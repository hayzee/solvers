(ns examples.mini-soduko-test
  (:require [clojure.test :refer :all])
  (:require [examples.mini-soduko :refer :all]))

;; todo: Still a W.I.P. !!!!!

; setup

(def solved-state    ; goal state, valid solution
  [[1 2 3]
   [3 1 2]
   [2 3 1]])

(def unsolved-state  ; not terminal
  [[1 2 nil]
   [nil 1 nil]
   [nil 3 1]])

(def failed-state    ; i.e. an invalid terminal state (duplicate in col 3)
  [[1 2 1]
   [3 1 2]
   [2 3 1]])

(def test-state    ; i.e. not a valid state but useful for test
  [[1 2 3]
   [4 5 6]
   [7 8 9]])

; tests

(deftest size-test
  (is (= 3 (size unsolved-state))))


(deftest rows-test
  (is (= [[1 2 3]
          [3 1 2]
          [2 3 1]] (rows solved-state))))


(deftest row-test
  (is (= [1 2 3] (row solved-state 0)))
  (is (= [3 1 2] (row solved-state 1)))
  (is (= [2 3 1] (row solved-state 2))))


(deftest cols-test
  (is (= [[1 3 2]
          [2 1 3]
          [3 2 1]] (cols solved-state))))


(deftest col-test
  (is (= [1 3 2] (col solved-state 0)))
  (is (= [2 1 3] (col solved-state 1)))
  (is (= [3 2 1] (col solved-state 2))))


(deftest row-col-vals-test
  (is (= #{1 2 3} (row-col-vals solved-state [0 0])))
  (is (= #{1 2 3} (row-col-vals solved-state [0 1])))
  (is (= #{1 2 3} (row-col-vals solved-state [0 2])))
  (is (= #{1 2 3} (row-col-vals solved-state [1 0])))
  (is (= #{1 2 3} (row-col-vals solved-state [1 1])))
  (is (= #{1 2 3} (row-col-vals solved-state [1 2])))
  (is (= #{1 2 3} (row-col-vals solved-state [2 0])))
  (is (= #{1 2 3} (row-col-vals solved-state [2 1])))
  (is (= #{1 2 3} (row-col-vals solved-state [2 2]))))


(deftest find-empties-test
  (is (not (empty? (find-empties unsolved-state))) "An unsolved state must have at least one possible move")
  (is (empty? (find-empties solved-state)) "A solved state cannot have any valid moves")
  (is (empty? (find-empties failed-state)) "A failed state cannot have any valid moves")
  (is (= (find-empties unsolved-state) '([0 2] [1 0] [1 2] [2 0])) "Unsolved state should generate specific set of empties"))


(deftest term?-test
  (is (= false (term? unsolved-state)) "An unsolved state cannot be in a terminal state")
  (is (= true (term? solved-state)) "An solved state must be in a terminal state")
  (is (= true (term? failed-state)) "A failed state must be in a terminal state"))


(deftest tg?-test
  (is (= false (tg? unsolved-state)) "An unsolved state cannot be in a target state")
  (is (= true (tg? solved-state)) "An solved state must be in the target state")
  (is (= false (tg? failed-state)) "A failed state cannot be in a target state"))


(comment

  (tg? unsolved-state)
  (tg? solved-state)
  (tg? failed-state)

  ;test
  (find-empties st)

  ; test f-mv
  (gen-moves st)

  ;test
  (apply-move st [[0 2] :ok])
  (apply-move st [[1 0] :ok])
  (apply-move st [[2 1] :ok])

  (map #(apply-move st %) (gen-moves st))

  )
