(ns restaurantapp.handler
 (:require [compojure.api.sweet :refer :all]
           [ring.util.http-response :refer :all]
  ))


  (def app
       (api
         {:swagger
          {:ui "/"
           :spec "/swagger.json"
           :data {:info {:title "Restaurant API"
                         :description "Compojure Api example"}
                  :tags [{:name "api"}
                        ]}}}

         (context "/api" []
           :tags ["api"]

           ;; implement http methods here

           )))


