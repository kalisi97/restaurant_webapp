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
               (def customersFound (q/get-customers))
               (if customersFound (ok customersFound) (not-found)))
             (GET "/:CustomerId" []
               :path-params [CustomerId :- schema/Num]
               :return Customer
               :summary "Retrieve customer by id."
               (def customerFound (q/get-customer CustomerId))
              (if customerFound (ok customerFound) (not-found)))
             (POST "/" []
               :summary "Add new customer"
               :body [customer-data SaveOrUpdateCustomer]
               (let [{:keys [Name Contact]} customer-data]
                 (ok (q/add-customer Name Contact))))
             (PUT "/:CustomerId" [CustomerId]
               :summary "Update customer information"
               :body [customer-data SaveOrUpdateCustomer]
               (let [{:keys [Name Contact]} customer-data]
                 (ok {:response (q/update-customer CustomerId Name Contact)})))
             (DELETE "/:CustomerId" []
               :summary "Delete customer"
               :path-params [CustomerId :- schema/Num]
               (ok {:response (q/delete-customer CustomerId)})))

           (context "/items" []
             :tags ["items"]
             (GET "/" []
               :return [Item]
               :summary "Get all items"
               (def itemsFound (q/get-items))
               (if itemsFound (ok itemsFound) (not-found)))
             (GET "/:ItemId" []
               :summary "Get item by id"
               :return Item
               :path-params [ItemId :- schema/Num]
               (def itemFound (q/get-item ItemId))
               (if itemFound (ok itemFound) (not-found))))

           )))


