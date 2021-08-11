(ns examples.number-pairs
  (:require [solvers.core :as slv])
  )

; Produce all numbers pairs with digits that sum to 13

(defn number-pairs []
  (slv/solver
    []
    #(and (= (count %) 2) (= (reduce + %) 13))
    #(= (count %) 2)
    (fn [_] (range 10))
    #(conj %1 %2)))
