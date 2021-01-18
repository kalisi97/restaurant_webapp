(ns restaurantapp.domain
  (:refer-clojure :exclude [update])
  (:require [korma.core :refer :all]
            [korma.db :refer :all]
            [restaurantapp.database :refer [db]]
            [schema.core :as schema]
            [clojure.core :as core ]))


(declare customer)
(declare orderitems)
(declare item)
(declare customerorder)


;; this is from korma, symbol must be equal to the name of the SQL table we use

(defentity item
           (pk :ItemId)
           (entity-fields :ItemId :Name :Price))

;; in order to perform join sql operation relations are set with the has-many korma.core macro

(defentity customer
           (pk :CustomerId)
           (has-many customerorder {:fk :CustomerId})
           (entity-fields :CustomerId :Name :Contact))

(defentity customerorder
           (pk :OrderId)
           (has-many orderitems {:fk :OrderId})
            (belongs-to customer {:fk :CustomerId})
           )

(defentity orderitems
           (pk :OrderItemId)
           (belongs-to customerorder {:fk :OrderId})
           (entity-fields :OrderItemId :OrderId :ItemId :Quantity))

;; schemas to standardize requests and responses

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
   :PMethod    schema/Str
   :GTotal     schema/Num
   :orderitems [OrderItem]
   :CustomerId schema/Num
   :CustomerId_2 schema/Num
   :Name schema/Str
   :Contact schema/Str
   })

(schema/defschema Item
  {
   :ItemId schema/Num
   :Name   schema/Str
   :Price  schema/Num
   })

(schema/defschema SaveOrUpdateItem
  {
   :Name       schema/Str
   :Price  schema/Num
   })

(schema/defschema SaveOrUpdateCustomer
  {
   :Name   schema/Str
   :Contact  schema/Str
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


