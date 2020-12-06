(ns restaurantapp.database
      (:require [korma.db :as korma]))


  (def db-connection (korma/mysql {:classname   "com.mysql.cj.jdbc.Driver"
                                   :subprotocol "mysql"
                                   :user        "root"
                                   :password    "Ipsilon97!"
                                   :subname     "//localhost:3306/restaurantdb"}))

  (korma/defdb db db-connection)


