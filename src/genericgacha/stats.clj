(ns genericgacha.stats
  (:gen-class)
  (:require [clojure.math.numeric-tower :as nt]))

(defn binomial-coefficient [n k]
  "Calulates the binomial coefficient given n trials and k successes"
  (let [rprod (fn [a b] (reduce * (range a (inc b))))]
    (/ (rprod (- n k -1) n) (rprod 1 k))))

(defn binomial-probability [n k p]
  "Calculates the binomial probability given n trials, k successes
  and p probability of success in a single trial"
  (if (and (<= p 1) (>= p 0))
    (* (binomial-coefficient n k)
       (nt/expt p k)
       (nt/expt (- 1 p) (- n k)))
    0))

(defn upper-tail-probability [n p lower-bound]
  "Calculates the probability P(X=>Y). Y = lower-bound"
  (reduce + (map (fn [x] (binomial-probability n x p))
                 (range lower-bound (inc n)))))

(defn lower-tail-probability [n p upper-bound]
  "Calculates the probability P(X<=Y). Y = upper-bound"
  (reduce + (map (fn [x] (binomial-probability n x p))
                 (range 0 (inc n)))))

(defn specific-within-tier-probability [tier-probability size]
  "Calculates the probability of getting a specific thing in a rarity tier"
  (/ tier-probability size))

(defn range-probability [n p lower-bound upper-bound]
  "Calulates the probability P(a<=X<=b) a=lower-bound, b=upper-bound"
  (- (lower-tail-probability n p upper-bound)
     (lower-tail-probability n p lower-bound)))
