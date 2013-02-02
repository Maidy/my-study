
(* 1. *)
is_older ((1999, 12, 22), (2000, 1, 22));
(* >> true *)
is_older ((2000, 12, 22), (2000, 12, 22));
(* >> false *)
is_older ((1999, 12, 22), (1999, 1, 22));
(* >> false *)
is_older ((2000, 1, 25), (1999, 1, 23));
(* >> false *)

(* 2. *)
number_in_month ([(2000, 1, 1), (2000, 2, 1), (2000, 3, 1), (2001, 2, 1)], 2);
(* >> 2 *)
number_in_month ([(2000, 1, 1), (2000, 3, 1)], 2);
(* >> 0 *)
number_in_month ([], 2);
(* >> 0 *)

(* 3. *)
number_in_months ([(2000, 1, 1), (2000, 2, 1), (2000, 3, 1), (2001, 2, 1)], [2, 3]);
(* >> 3 *)

(* 4. *)
dates_in_month ([(2000, 1, 1), (2000, 2, 1), (2000, 3, 1), (2001, 2, 1)], 2);
(* >> [(2000, 2, 1), (2001, 2, 1)] *)

(* 5. *)
dates_in_months ([(2000, 1, 1), (2000, 2, 1), (2000, 3, 1), (2001, 2, 1)], [2, 3]);
(* >> [(2000, 2, 1), (2001, 2, 1), (2000, 3, 1)] *)

(* 6. *)
get_nth (["a", "b", "c", "d"], 2);
(* >> "b" *)

(* 7. *)
date_to_string (1999, 12, 22);
(* >> "December 22, 1999" *)

(* 8. *)
number_before_reaching_sum (10, [1, 2, 3, 4, 5, 6]); (* 3  *)
number_before_reaching_sum (11, [1, 2, 3, 4, 5, 6]); (* 4  *)
number_before_reaching_sum (1, [2]); (* 0 *)
number_before_reaching_sum (5, [3, 1, 2]); (* 2 *)
number_before_reaching_sum (5, [3, 2, 2]); (* 1 *)
number_before_reaching_sum (4, [1, 4, 4, 1]); (* 1 *)
number_before_reaching_sum (6, [4, 1, 1, 1]); (* 2 *)
number_before_reaching_sum (10, [1, 2, 3, 4, 5]); (* 3 *)

(* 9. *)
what_month(1);   (* >> 1  *)
what_month(31);  (* >> 1  *)
what_month(32);  (* >> 2  *)
what_month(365); (* >> 12  *)

(* 10. *)
month_range (30, 34);
(* >> [1, 1, 2, 2, 2] *)

(* 11. *)
oldest ([(1999, 10, 30), (1999, 9, 2), (1999, 10, 2), (1999, 8, 24)]);
(* >> SOME (1999, 8, 24) *)
oldest ([]);
(* >> NONE *)

(* 12. *)
contains (2, [1, 2, 3, 4]);
(* >> true *)
contains (5, [1, 2, 3, 4]);
(* >> false *)
contains (2, []);
(* >> false *)

remove_duplicates ([1, 2, 3, 2]);
(* >> [1, 3, 2] *)
remove_duplicates ([1, 2, 3, 2, 3, 2]);
(* >> [1, 3, 2] *)
remove_duplicates ([1]);
(* >> [1] *)
remove_duplicates ([]);
(* >> [] *)

number_in_months_challenge ([(2000, 1, 1), (2000, 2, 1), (2000, 3, 1), (2001, 2, 1)], [2, 3, 2, 3]);
(* >> 3 *)

dates_in_months_challenge ([(2000, 1, 1), (2000, 2, 1), (2000, 3, 1), (2001, 2, 1)], [2, 3, 2, 3]);
(* >> [(2000, 2, 1), (2001, 2, 1), (2000, 3, 1)] *)

(* 13. *)
reasonable_date (2012, 2, 29);
(* >> true *)
reasonable_date (2013, 2, 29);
(* >> false *)
reasonable_date (0, 2, 29);
(* >> false *)
reasonable_date (1999, 12, 0);
(* >> false *)
