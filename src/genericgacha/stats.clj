(ns genericgacha.stats
  (:gen-class)
  (:require [clojure.math.numeric-tower :as nt]))

(defn binomial-coefficient [n k]
  (let [rprod (fn [a b] (reduce * (range a (inc b))))]
    (/ (rprod (- n k -1) n) (rprod 1 k))))

(defn binomial-probability [n k p]
  (if (and (<= p 1) (>= p 0))
    (* (binomial-coefficient n k) (nt/expt p k) (nt/expt (- 1 p) (- n k)))
    0))

(defn upper-tail-probability [n p lower-bound]
  (reduce + (map (fn [x] (binomial-probability n x p))
                 (range lower-bound (inc n)))))

(defn lower-tail-probability [n p upper-bound]
  (reduce + (map (fn [x] (binomial-probability n x p))
                 (range 0 (inc upper-bound)))))
