(defproject restaurantapp-backend "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [metosin/compojure-api "2.0.0-alpha30"]
                 [korma "0.4.3"]
                 [mysql/mysql-connector-java "8.0.12"]]
  :ring {:handler restaurantapp.handler/app}
  :profiles {:dev {:plugins [[lein-ring "0.12.5"]]}}
  :repl-options {:init-ns webapp-backend.core})

