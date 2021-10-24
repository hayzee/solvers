(ns examples.mini-soduko
  (:require [solvers.core :as solvers]))

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
;; ================


(defn size
  "The size of a board is the count of its rows."
  [st]
  (count st))


(defn rows
  "Can just return the board for this, as it is oriented by rows anyway."
  [st]
  st)


(defn cols
  "Return each column."
  [st]
  (apply map vector st))


(defn find-empties
  "Return the row col pairs or all empty positions on the board."
  [st]
  (for [r (range (size st))
        c (range (size st))
        :when (= (get-in st [r c]) nil)]
    [r c]))


(defn first-empty
  "Return the first available move position [r c] as a list - or an empty list if there are no moves."
  [st]
  (take 1 (find-empties st)))


(defn tg?
  "If all rows and all columns are sets #{1 2 ... (size st)}."
  [st]
  (let [target-set (set (range 1 (inc (size st))))]
       (and
         (every? #(= target-set %) (map set (cols st)))
         (every? #(= target-set %) (map set (rows st))))))


(defn term?
  "If all positions are filled, then there are no more moves and this is a terminal state!"
  [st]
  (= (int (Math/pow (size st) 2)) (count (remove nil? (apply concat st)))))


;; todo: This still isn't quite right but everything seems to be working now
;; todo: .. because first-empty is either a single-valued list or is empty
;; todo: .. making the for unnecessary.
(defn f-mv
  "Given a state `st, generate all possible future moves '[[r c] v] - (r)ow, (c)olumn (v)alue."
  [st]
  (for [e (first-empty st)
        n (range 1 (inc (size st)))
        ]
    [e n]))


(defn f-apmv
  "Given a state `st, and a move `[[r c] v] apply the move to the state."
  [st [[r c] v]]
  (assoc-in st [r c] v))


(defn solve
  "Find all solutions to `st."
  [st]
  (solvers/solver st tg? term? f-mv f-apmv))

