(ns examples.number-pairs
  (:require [solvers.core :as slv])
  )

; Produce all numbers pairs with digits that sum to 13

(defn number-pairs []
  (slv/solver
    []                                              ; st     => empty vector
    #(and (= (count %) 2) (= (reduce + %) 13))      ; tg?    => state has 2 digits and they sum to 13
    #(= (count %) 2)                                ; term?  => state has 2 digits
    (fn [_] (range 10))                             ; f-mv   => a list of all digits
    #(conj %1 %2)                                   ; f-apmv => conj a digit to a state
    ))
