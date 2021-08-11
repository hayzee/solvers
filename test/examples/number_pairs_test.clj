(ns examples.number-pairs-test
  (:require [clojure.test :refer :all])
  (:require [examples.number-pairs :refer [number-pairs]]))

(deftest number-pairs-test
  (is (= (set (number-pairs)) #{[4 9] [5 8] [6 7] [7 6] [8 5] [9 4]})))
