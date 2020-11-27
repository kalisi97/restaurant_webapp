(ns restaurantapp.domain
  (:require [korma.core :refer :all]
            [korma.db :refer :all]
            [restaurantapp.database :refer [db]]
            [schema.core :as schema]
            [clojure.core :as core]))


(declare customer)
(declare orderitems)
(declare item)
(declare customerorder)

(defentity item
           (pk :ItemId)
           (entity-fields :ItemId :Name :Price))

(defentity customer
           (pk :CustomerId)
           (has-many customerorder {:fk :CustomerId})
           (entity-fields :CustomerId :Name :Contact))

(defentity customerorder
           (pk :OrderId)
           (has-many orderitems {:fk :OrderId})
           (belongs-to customer {:fk :CustomerId}))

(defentity orderitems
           (pk :OrderItemId)
           (belongs-to customerorder {:fk :OrderId})
           (entity-fields :OrderItemId :OrderId :ItemId :Quantity))

