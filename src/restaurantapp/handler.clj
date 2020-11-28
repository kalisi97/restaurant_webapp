(ns restaurantapp.handler
 (:require [compojure.api.sweet :refer :all]
           [ring.util.http-response :refer :all]
           [restaurantapp.queries :as q]
           [schema.core :as schema]
           [restaurantapp.domain :refer :all]))


  (def app
       (api
         {:swagger
          {:ui "/"
           :spec "/swagger.json"
           :data {:info {:title "Restaurant API"
                         :description "Compojure Api example"}
                  :tags [{:name "api"}
                         {:name "customers"}
                         {:name "items"}
                        ]}}}

         (context "/api" []
           :tags ["api"]

           (context "/customers" []
             :tags ["customers"]
             (GET "/" []
               :return [Customer]
               :summary "Retrieve all customers."
               (ok (q/get-customers)))
             (GET "/:CustomerId" []
               :path-params [CustomerId :- schema/Num]
               :return Customer
               :summary "Retrieve customer by id."
               (ok (q/get-customer CustomerId)))
             (POST "/" []
               :summary "Add new customer"
               :body [customer-data SaveOrUpdateCustomer]
               (let [{:keys [Name Contact]} customer-data]
                 (ok (q/add-customer Name Contact))))
             (PUT "/:CustomerId" [CustomerId]
               :summary "Update customer information"
               :body [customer-data SaveOrUpdateCustomer]
               (let [{:keys [Name Contact]} customer-data]
                 (ok {:updated (q/update-customer CustomerId Name Contact)})))
             (DELETE "/:CustomerId" []
               :summary "Delete customer"
               :path-params [CustomerId :- schema/Num]
               (ok {:deleted (q/delete-customer CustomerId)}))
             )

           (context "/items" []
             :tags ["items"]
             (GET "/" []
               :return [Item]
               :summary "Get all items"
               (ok (q/get-items)))
             (GET "/:ItemId" []
               :summary "Get item by id"
               :return Item
               :path-params [ItemId :- schema/Num]
               (ok (q/get-item ItemId))))



           )))


