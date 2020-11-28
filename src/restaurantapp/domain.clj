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

(schema/defschema OrderItem
  {
   :OrderItemId schema/Num
   :OrderId     schema/Num
   :ItemId      schema/Num
   :Quantity    schema/Num
   })

(schema/defschema Customer
  {
   :CustomerId schema/Num
   :Name       schema/Str
   :Contact    schema/Str
   })

(schema/defschema Order
  {
   :OrderId    schema/Num
   :CustomerId schema/Num
   :PMethod    schema/Str
   :GTotal     schema/Num
   :orderitems [OrderItem]
   })

(schema/defschema Item
  {
   :ItemId schema/Num
   :Name   schema/Str
   :Price  schema/Num
   })

(schema/defschema SaveOrUpdateCustomer
  {
   :Name       schema/Str
   :Contact    schema/Str
   })

(schema/defschema NewOrderItem
  {
   :ItemId      schema/Num
   :Quantity    schema/Num
   })

(schema/defschema NewOrder
  {
   :CustomerId schema/Num
   :PMethod    schema/Str
   :GTotal     schema/Num
   :OrderItems  [NewOrderItem]
   })


