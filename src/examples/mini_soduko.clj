(ns examples.mini-soduko)

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
;; Is in an invalid state because columns 2 and 3 contain repeats
;;
;;

(defn size
  ; The size of a board is the count of its rows.
  [st]
  (count st))

(defn rows
  ; Can just return the board for this as it is oriented by rows anyway
  [st]
  st)

(defn cols
  ; Return each column
  [st]
  (apply map vector st))

(defn term?
  ; If all rows are filled, then there are no more moves and this is a terminal state!
  [st]
  (->>
    (map #(count (remove nil? %)) (rows st))
    (every? #(= (size st) %))))

(defn tg?
  ;; Is all rows and all columns add up to 9
  [st]
  (and
    (term? st)
    (= 9 (reduce + (map #(count (distinct %)) (rows st))))
    (= 9 (reduce + (map #(count (distinct %)) (cols st))))))

(defn find-empties [st]
  (for [r (range size)
        c (range size)
        :when (= (get-in st [r c]) nil)
        ]
    [r c]))


(defn gen-moves [st]
  (for [e (find-empties st)
        n (range 1 (inc size))
        ]
    [e n]))

(defn apply-move [st [[r c] v]]
  (assoc-in st [r c] v))


