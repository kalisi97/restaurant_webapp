(ns restaurantapp.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [restaurantapp.queries :as q]
            [schema.core :as schema]
            [ring.middleware.cors :refer [wrap-cors]]
            [restaurantapp.domain :refer :all]
            [compojure.route :as route]))


(def app
  (->
    (api
      {:swagger
       {:ui "/"
        :spec "/swagger.json"
        :data {:info {:title "Restaurant API"
                      :description "Compojure Api example"}
               :tags [{:name "api"}
                      {:name "customers"}
                      {:name "items"}
                      {:name "orders"}
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
              (def createdCustomer (q/add-customer Name Contact))
              (ok {:response "Customer inserted!"})))
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
            (if itemFound (ok itemFound) (not-found)))
          (POST "/" []
            :summary "Add new item"
            :body [item-data SaveOrUpdateItem]
            (let [{:keys [Name Price]} item-data]
              (def itemInserted (q/add-item Name Price))
              (ok itemInserted))
            )
          (PUT "/:ItemId" [ItemId]
            :summary "Update item information"
            :body [item-data SaveOrUpdateItem]
            (let [{:keys [Name Price]} item-data]
              (ok {:response (q/update-item ItemId Name Price)})))
          (DELETE "/:ItemId" [ItemId]
            :summary "Delete item"
            :path-params [ItemId :- schema/Num]
            (ok {:response (q/delete-item ItemId)}))
          )

        (context "/orders" []
          :tags ["orders"]
          (GET "/" []
            :summary "Get all orders"
            :return [Order]
            (ok (q/get-orders)))
          (POST "/" []
            :body [new-order NewOrder]
            (let [{:keys [CustomerId PMethod GTotal OrderItems]} new-order])
            (doseq [item [(get new-order :OrderItems)]]
              (let [{:keys [ItemId Quantity]} item]))
            (ok {:response (q/add-order new-order (get new-order :OrderItems)) }))
          (DELETE "/:OrderId" [OrderId]
            :summary "Delete order"
            :path-params [OrderId :- schema/Num]
            (ok {:response (q/delete-order OrderId)}))
          )
        (route/not-found
          (not-found {:body "No such endpoint."}))
        ))
    (wrap-cors :access-control-allow-origin #"http://localhost:4200"
               :access-control-allow-methods [:get :put :delete :post])
    ))


