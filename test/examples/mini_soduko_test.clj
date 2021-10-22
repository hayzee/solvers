(ns examples.mini-soduko-test
  (:require [clojure.test :refer :all])
  (:require [examples.mini-soduko :refer :all]))

;; todo: Still a W.I.P. !!!!!

; setup

(def solved-state
  [[1 2 3]
   [3 1 2]
   [2 3 1]])

(def unsolved-state  ; not terminal
  [[1 2 nil]
   [nil 1 nil]
   [nil 3 1]])

(def failed-state    ; i.e. an invalid terminal state
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


