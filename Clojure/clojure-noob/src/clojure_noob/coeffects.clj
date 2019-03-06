(ns clojure-noob.coeffects
  (:require [re-frame.core :refer :all]))

; coeffects is the current state of the world, as data, as presented to an event handler.

(comment

  ; The following functions delivers only the coeffect db to the event handles, as well as the event
  ; reg-event-db
  (fn [db event]
   ... return updated-db)

  ; Sometimes we need to know more than just the world, we need more inputs that are called coeffects
  ; When more than application state is needed (reg-event-fx)
  ; The first argument coeffects is a map. One of the keys is :db, which contains the current application state
  ; It also has other keys with relevant aspects of the world, so `coeffects` is a superset of `db`
  (fn [coeffects event]
    ... return a map of effects))

; Example

; We need a handler that is capable of retrieving the data from local store. One of the possible solutions would be
(reg-event-db
  :load-defaults
  (fn [db _]
    (let [val (js->clj (.getItem js/localStorage "defaults-key"))]  ;; <-- Problem
      (assoc db :defaults val))))

; The problem with the approach above is that we are directly accessing the LocalStorage, which makes this handler
; a handler with side effects. In order to turn it into a pure function, we need to work only with the passed args
; To make this change we need to switch `reg-event-db` for `reg-event-fx`, so we can access to other values

(reg-event-fx                     ;; note: -fx
  :load-defaults
  (fn [cofx event]                 ;; cofx means coeffects
    (let [val (:local-store cofx)  ;; <-- get data from cofx
          db  (:db cofx)]          ;; <-- more data from cofx
      {:db (assoc db :defaults val)}))) ;; returns an effect

; cofx magically contains the necessary key :local-store, but we need to understand how this value is inputted there
; Each time an event is handled, a new context is created (map). The context map will contain a :coeffects key, which
; is initially empty. Each interceptor in the event handler chain has a :before function that can assoc new values for
; the :coeffects key; thus, when we reach the handler at the end of the chain, `cofx` will have the necessary data.

; Which interceptors
; In order to append the needed data to cofx, we need to set the proper interceptors. E.g. :

(reg-event-fx
  :load-defaults
  ; inject-cofx is a function that returns an interceptor whose :before function loads a key/value pair into context's
  ; :coeffects map. The first argument is the id (called a cofx-id), the second is an optional additional value
  ; In our case the :local-store is the key, and the defaults-key is the additional value
  ; We can add as many interceptors as we want. E.g.
  ; [(inject-cofx :random-int 10) (inject-cofx :now)  (inject-cofx :local-store "blah")]
  [ (inject-cofx :local-store "defaults-key") ]     ;; <-- this is new
  (fn [cofx event]
    (let [val (:local-store cofx)
          db  (:db cofx)]
      {:db (assoc db :defaults val)})))

; reg-cofx is responsible for associate a cofx-id (e.g. :local-store) with a handler function that injects the right
; pair of key/value. The function to register will have two arguments. the :coeffects map and the optional additional
; value. For example, we can have a cofx-id :now that will store the current date on :now inside the cofx map

(reg-cofx               ;; registration function
  :now                 ;; what cofx-id are we registering
  (fn [coeffects _]    ;; second parameter not used in this case
    (assoc coeffects :now (js.Date.))))   ;; add :now key, with value

; Because we declared the cofx handler above, we can add an interceptor to a reg-even-fx like this
(reg-event-fx
  :load-defaults
  [(inject-cofx :now)]
  (fn [cofx _]
    nil))

; The key :now will be injected into cofx with the desired value
; So, we can only use (inject-cofx :keyword) when we have already created a handler for the :keyword
; Note: There is also a (inject-cofx :db) in front of every chain, which is why :db is always available
