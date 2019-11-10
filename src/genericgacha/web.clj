(ns genericgacha.web
  (:gen-class)
  (:use ring.util.response)
  (:use ring.middleware.resource)
  (:use ring.middleware.content-type)
  (:use ring.middleware.not-modified)
  (:use ring.middleware.gzip)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [genericgacha.stats :as stats]))



(def resourced-handler
  (-> (fn [request]
        (-> (resource-response "public/index.html")
            (content-type "text/html")))
      (wrap-resource "public")
      (wrap-content-type)
      (wrap-not-modified)
      (wrap-gzip)))

(def not-found-handler
  (-> (fn [request]
        (-> (resource-response "public/not-found.html")
            (content-type "text/html")))
      (wrap-resource "public")
      (wrap-content-type)
      (wrap-not-modified)
      (wrap-gzip)))


(def css-handler
  (-> (fn [request]
        (-> (resource-response "public/css/bulma.min.css")
            (content-type "text/css")))
      (wrap-resource "public")
      (wrap-content-type)
      (wrap-not-modified)
      (wrap-gzip)))

(defroutes index-handler
  (GET "/" [] resourced-handler)
  (GET "/css/bulma.min.css" [] css-handler)
  (route/resources "/")
  (route/not-found not-found-handler))
