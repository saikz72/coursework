exception NotImplemented
exception Error

(* Q1 The type of nucleobase. *)
type nucleobase = T | A | C | G


(* Q2 *)
type exp = 
  | PLUS  of exp * exp  (* Plus *)
  | MINUS of exp * exp  (* Minus *)
  | MULT of exp * exp  (* Mult *)
  | DIV   of exp * exp  (* Div *)
  | SIN   of exp        (* Sin *)
  | COS   of exp        (* Cos *)
  | EXP   of exp        (* Exp *)
  | FLOAT of float


type instruction = Plus | Minus | Mult | Div | Sin | Cos | Exp | Float of float

type stack = float list

(* Reminder: If a test case requires multiple arguments, use a tuple:
let myfn_with_2_args_tests = [
  ((arg1, arg1), (expected_output))
]
*)

(* Q1 *)
(* TODO: Write a good set of tests for compress *)
let compress_tests = [
  ([A;A;A;A;G;G;A;T;T;T;C;T;C], [(4,A);(2,G);(1,A);(3,T);(1,C);(1,T);(1,C)]);
  ([A], [(1, A)]);
  ([], []);
]

(* TODO: Implement compress. *)
let compress (l : nucleobase list) : (int * nucleobase) list = 
  let rec helper l n = match l with 
    | [] -> []
    | [h] -> (n, h)::[]
    | h::((x::tail) as xs) -> 
        if h = x then
          helper xs (n + 1)
        else
          (n, h)::helper xs 1
  in 
  helper l 1
  

(* TODO: Write a good set of tests for decompress *)
let decompress_tests = [
  ([(4,A);(2,G);(1,A);(3,T);(1,C);(1,T);(1,C)], [A;A;A;A;G;G;A;T;T;T;C;T;C]);
  ([(1,A)],[A]);
  ([], []); 
] 

(* TODO: Implement decompress. *)
let rec decompress (l : (int * nucleobase) list) : nucleobase list = match l with
  | [] -> []
  | (i,n)::tail ->
      let rec helper l i =
        if i > 0 then
          n::helper l (i -1)
        else
          decompress tail 
      in
      helper l i
      
  
          
(* Q2 *)
(* TODO: Write a good set of tests for eval *)
let eval_tests = [
  ((MULT (PLUS (FLOAT 2.2, FLOAT 3.3), FLOAT 5.0)), 27.5); 
  ((FLOAT 3.4), 3.4);
  ((EXP(FLOAT 0.5)), 1.648721271); 
  (MINUS(FLOAT 2.5, FLOAT 2.0), 0.5);
  (COS(FLOAT 30.0), 0.15425145);
]

(* TODO: Implement eval. *)
let rec eval e = match e with
  | FLOAT ex -> ex 
  | PLUS (ex, ex2) -> eval ex +. eval ex2
  | MINUS (ex, ex2) -> eval ex -. eval ex2
  | MULT (ex, ex2) -> eval ex *. eval ex2
  | DIV (ex, ex2) -> eval ex /. eval ex2
  | SIN ex -> sin (eval ex)
  | COS ex -> cos (eval ex)
  | EXP ex -> exp (eval ex)

(* TODO: Write a good set of tests for to_instr *)
let to_instr_tests = [
  ((MULT (PLUS (FLOAT 2.2, FLOAT 3.3), FLOAT 5.0)), [Float 2.2; Float 3.3; Plus; Float 5.; Mult]);
  ((FLOAT 2.4), [Float 2.4]);
  ((SIN(FLOAT 5.0), [Float 5.0; Sin])); 
]

(* TODO: Implement to_instr. *)
let rec to_instr e = match e with 
  | FLOAT ex -> [Float(ex)]
  | SIN ex -> to_instr ex @ [Sin]
  | COS ex -> to_instr ex @ [Cos]
  | EXP ex -> to_instr ex @ [Exp]
  | PLUS (ex, ex2) -> to_instr ex @ to_instr ex2 @ [Plus]
  | MINUS (ex, ex2) -> to_instr ex @ to_instr ex2 @ [Minus]
  | MULT (ex, ex2) -> to_instr ex @ to_instr ex2 @ [Mult]
  | DIV (ex, ex2) -> to_instr ex @ to_instr ex2 @ [Div]

(* TODO: Write a good set of tests for instr *)
let instr_tests = [
  ((Minus, ([4.4])), None); 
  ((Plus, [2.3; 4.2]), Some[6.5]); 
  ((Cos, [30.0]), Some[0.15425145]);
  ((Exp, [0.5;2.0]), Some [1.64872127; 2.0]);
  ((Float 4.2, [3.2]), Some [4.2;3.2]);
  
]


(* TODO: Implement to_instr. *)               
let instr i s = match (i,s) with 
  | (_, []) -> None
  | (Float (ex), _) -> Some (ex::s)
  | (Mult, [i]) | (Div, [i]) | (Plus, [i]) | (Minus, [i]) -> None
  | (Mult, (h::(x::tail))) -> Some (x *. h::tail)
  | (Div, (h::(x::tail))) -> Some (x /. h::tail) 
  | (Plus, (h::(x::tail))) -> Some (x +. h::tail)
  | (Minus, (h::(x::tail))) -> Some (x -. h::tail)
  | (Sin, (h::tail)) -> Some (sin h::tail)
  | (Cos, (h::tail)) -> Some (cos h::tail)
  | (Exp, (h::tail)) -> Some (exp h::tail)


(* TODO: Write a good set of tests for prog *)
let prog_tests = [
  ([Float 2.2; Float 3.3; Plus; Float 5.; Mult], Some 27.5)
]

(* TODO: Implement prog. *)
let prog instrs = 
  let rec helper instrs stack = match (instrs, stack) with 
    | ([], [h']) -> Some (h') 
    | ([], _ ) -> None
    | (h::tail, h'::((x'::tail') as xs)) -> begin match h with
        | Float ex -> helper tail (ex::stack)
        | Sin -> helper tail ([sin h'])
        | Cos -> helper tail ([cos h']) 
        | Exp -> helper tail ([exp h'])
        | Plus -> helper tail ([x' +. h'])
        | Minus -> helper tail ([x' -. h'])
        | Div -> helper tail ([x' /. h'])
        | Mult -> helper tail ([x' *. h']) end
  in
  helper instrs []
  
    
let rec helper instrs stack = match instrs with
  |[] -> begin match stack with
      | [x] -> Some x
      | _ -> None
    end
  |h::tail -> let stack_option = instr h stack in
      begin match stack_option with
        | None -> None
        | Some list -> helper tail list
      end 


let rec stack instrs = match instrs with 
  | [] -> []
  | (Float ex)::tail -> ex::stack tail
  | _::tail -> stack tail
                 
let rec instructionList instrs = match instrs with
  | [] -> []
  | (Float ex)::tail -> instructionList tail
  | h::tail -> h::instructionList tail 
                 
let prog2 instrs = 
  let floatList = stack instrs in
  let instList = instructionList instrs in
  helper instList floatList
    
    

                 
















