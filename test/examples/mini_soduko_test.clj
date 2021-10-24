(ns examples.mini-soduko-test
  (:require [clojure.test :refer :all])
  (:require [examples.mini-soduko :refer :all]))

; setup

(def solved-state                                           ; goal state, valid solution
  [[1 2 3]
   [3 1 2]
   [2 3 1]])

(def unsolved-state                                         ; not terminal
  [[1 2 nil]
   [nil 1 nil]
   [nil 3 1]])

(def failed-state                                           ; i.e. an invalid terminal state (duplicate in col 3)
  [[1 2 1]
   [3 1 2]
   [2 3 1]])

; tests

(deftest size-test
  (is (= 3 (size unsolved-state))))


(deftest rows-test
  (is (= [[1 2 3]
          [3 1 2]
          [2 3 1]] (rows solved-state))))


(deftest cols-test
  (is (= [[1 3 2]
          [2 1 3]
          [3 2 1]] (cols solved-state))))


(deftest find-empties-test
  (is (not (empty? (find-empties unsolved-state))) "An unsolved state must have at least one possible move")
  (is (empty? (find-empties solved-state)) "A solved state cannot have any valid moves")
  (is (empty? (find-empties failed-state)) "A failed state cannot have any valid moves")
  (is (= (find-empties unsolved-state) '([0 2] [1 0] [1 2] [2 0])) "Unsolved state should generate specific set of empties"))


(deftest first-empty-test
  (is (= (list [0 2]) (first-empty unsolved-state)))
  (is (= (list) (first-empty solved-state)) "There is no first empty space in a solved state")
  (is (= (list) (first-empty failed-state))) "There is no first empty space in an unsolved state")


(deftest tg?-test
  (is (= false (tg? unsolved-state)) "An unsolved state cannot be in a target state")
  (is (= true (tg? solved-state)) "An solved state must be in the target state")
  (is (= false (tg? failed-state)) "A failed state cannot be in a target state"))


(deftest term?-test
  (is (= false (term? unsolved-state)) "An unsolved state cannot be in a terminal state")
  (is (= true (term? solved-state)) "An solved state must be in a terminal state")
  (is (= true (term? failed-state)) "A failed state must be in a terminal state"))


(deftest f-mv-test
  (is (= (list [[0 2] 1] [[0 2] 2] [[0 2] 3]) (f-mv unsolved-state)) "All moves for an unsolved state is all possible entries in the first empty slot")
  (is (= (list) (f-mv solved-state)) "There are no moves for an unsolved state")
  (is (= (list) (f-mv failed-state)) "There are no moves for a failed state"))

(deftest f-apmv-test
  (is (= [[1 2 1] [nil 1 nil] [nil 3 1]] (f-apmv unsolved-state [[0 2] 1])))
  (is (= [[1 2 2] [nil 1 nil] [nil 3 1]] (f-apmv unsolved-state [[0 2] 2])))
  (is (= [[1 2 3] [nil 1 nil] [nil 3 1]] (f-apmv unsolved-state [[0 2] 3]))))


(deftest solve-test
  (is (= #{[[1 2 3]
            [3 1 2]
            [2 3 1]]}
         (set (solve unsolved-state))) "The unsolved-state has 1, and only 1, solution")

  (is (= #{[[1 2 3]
            [3 1 2]
            [2 3 1]]

           [[1 3 2]
            [2 1 3]
            [3 2 1]]}

         (set (solve [[1 nil nil]
                      [nil 1 nil]
                      [nil nil 1]]))) "The identity matrix has 2 solutions - which sounds clever, but isn't really")

  (is (= #{}

         (set (solve [[  1 nil nil]
                      [nil   2   3]
                      [  3 nil nil]]))) "An unsolvable solution has no solutions!!")

  )
