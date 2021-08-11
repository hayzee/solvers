(ns examples.mini-soduko)

(comment

;(defrecord coord [row col])

(def st [[1 2 nil]
         [3 99 nil]
         [nil 888 999]])

(update-in st [2 0] (constantly 6))

(defn moves [s]
  (for [x (range 3)
        y (range 3)
        :let [coord [x y]]
        :when (not (get-in st coord))]
    coord))

(moves st)

(defn app-moves [st moves]
  (map #(update-in st [2 0] (constantly 6))
    ))


  (slv/product ["this" "is" "a" "sentence"] ["and" "also" "qualifies" "as" "well"])

  (def allwords (slurp "https://raw.githubusercontent.com/dwyl/english-words/master/words.txt"))


  (def allwords2 (filter #(not= \newline %) (partition-by #(= % \newline) allwords)))

  (take 1000 (drop 1000 allwords2))

  )
