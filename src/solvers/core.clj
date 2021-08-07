(ns solvers.core)

(defn solver
  "
  Depth first backtracking solver

  st     state
  tg?    determines if state is in its target (solved) state
  term?  determines if state is in its terminal (unsolvable) state
  f-mv   creates new moves (not states) based on current state
  f-apmv applies moves to a state. Generates a new list of states [s] -> [s1, s2 ... sn]
  "
  [st tg? term? f-mv f-apmv]
  (cond
    (tg? st)    (list st)            ;; target case
    (term? st)  (list)               ;; terminating case
    :else (for [mv (f-mv st)         ;; recursive case
                ns (solver (f-apmv st mv) tg? term? f-mv f-apmv)]
            ns)))
