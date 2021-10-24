(ns examples.mini-soduko
  (:require [solvers.core :as solvers])
  )

;; todo: Still a W.I.P. !!!!!

;; A micro-suko board is a 3x3 board where each position can be an integer from 1..3.
;;
;; In a solved state it looks like this, but note that this isn't the only valid arrangement:
;;
;; [[1 2 3]
;;  [3 1 2]
;;  [2 3 1]]
;;
;; That is, all columns contain every number 1 .. 3 and all rows contain every number 1 .. 3
;
;; Thus:
;;
;; [[1 3 2]
;;  [3 1 2]
;;  [2 3 1]]
;;
;; ... is in an invalid state because columns 2 and 3 contain repeats
;;
;;


;; Helper functions

(defn size
  "The size of a board is the count of its rows."
  [st]
  (count st))


(defn rows
  "Can just return the board for this, as it is oriented by rows anyway."
  [st]
  st)


(defn row
  "Return the `rth row in `st"
  [st r]
  (nth st r))


(defn cols
  "Return each column"
  [st]
  (apply map vector st))


(defn col
  "Return the `cth col in `st"
  [st c]
  (map #(nth % c) st))


(defn row-col-vals
  "Returns the set of values (excluding nil) contained in the union of row 'r and col 'c in state 'st"
  [st [r c]]
  (set (filter identity (reduce conj (row st r) (col st c)))))




(defn legal-mv?
  "Return true if it is legal to apply the move `[[r c] v] to state `st. `[r c] in `st is assumed to be nil"
  [st [[r c] v]]
  (not (contains? v (row-col-vals st [r c])))
  )





(defn find-empties
  "Return the row col pairs or all empty positions on the board."
  [st]
  (for [r (range (size st))
        c (range (size st))
        :when (= (get-in st [r c]) nil)]
    [r c]))



(defn term?
  "If all rows are filled, then there are no more moves and this is a terminal state!"
  [st]
  (->>
    (map #(count (remove nil? %)) (rows st))
    (every? #(= (size st) %))))


(defn tg?
  "If all rows and all columns add up to 9, then it's target state."
  [st]
  (= {1 6, 2 6, 3 6} (frequencies (flatten (list (rows st) (cols st))))))


(defn f-mv
  "Given a state, generate a bunch of possible future moves"
  [st]
  (for [e (find-empties st)
        n (range 1 (inc (size st)))]
    [e n]))


(defn f-apmv [st [[r c] v]]
  (assoc-in st [r c] v))



(defn solve [st]
  (solvers/solver st tg? term? f-mv f-apmv))

