(ns genericgacha.web
  (:gen-class)
  (:use ring.util.response)
  (:use ring.middleware.resource)
  (:use ring.middleware.content-type)
  (:use ring.middleware.not-modified)
  (:use ring.middleware.gzip)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [genericgacha.stats :as stats]
            [oz.core :as oz]))




(def resourced-handler
  (-> (fn [request]
        (-> (resource-response "public/index.html")
            (content-type "text/html")))
      (wrap-resource "public")
      (wrap-content-type)))

(def not-found-handler
  (-> (fn [request]
        (-> (resource-response "public/not-found.html")
            (content-type "text/html")))
      (wrap-resource "public")
      (wrap-content-type)))

(defn start-oz-server [request]
  (oz/start-server!)
  (redirect "/"))


(defroutes index-routes
  (GET "/" [] resourced-handler)
  (GET "/startoz" [] start-oz-server)
  (route/resources "/")
  (route/not-found not-found-handler))

(def main-handler
  (-> index-routes
      (wrap-gzip)
      (wrap-not-modified)))
