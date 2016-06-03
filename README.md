# thamma / cube

> A Rubiks Cube implementation in Java which focuses on the more technical aspects of the cube. Thus more theory, analytics and math is available.

## Table of contents

* Implementation
  * The cube model
  * Turns
  * Algorithms
  * Interpreter
    * Grammar
    * Semantics
    * Syntactic Sugar
    * Examples
* Examples
  * Usage
  * Tools

## Implementation

### The cube model 

### Turns

### Algorithms

### Interpreter

#### Grammar
The interpreter interpretes expressions which suffice the grammar given by this [BNF](https://en.wikipedia.org/wiki/Backus%E2%80%93Naur_Form):
```
<exp> ::= <exp> <exp>* | <pexp>
<par_exp> ::= ( <exp> ) <appendix> | <com_exp> [appendix] | <turn_exp>
<com_exp> ::= [<exp> : <exp>] | [<exp> , <exp>]
<turn_exp> ::= <turn> [appendix]

<turn> ::= U | Uw | … | M | E | S | x | y | z
<appendix> ::= ['] [number]
<number> ::= ℕ
```

#### Semantics
```
⟦AB⟧ = ⟦A⟧ * ⟦B⟧
⟦A'⟧ = A⁻¹
⟦(A)n⟧ = A * … * A                     (n times)   ∀n∈ℕ
⟦[A:B]⟧ = A B A⁻¹
⟦[A,B]⟧ = A B A⁻¹ B⁻¹
```

#### Syntactic Sugar
Since the commutator notation is very powerful, it deserves some love considering the accumulation of brackets. Thus this syntactic sugar is justified:
```
[A: B, C] = [A: [B, C]]
```
Planned:
In future versions I might include this sweet:
```
[A °¹ B °² ... Z] = [A °¹ [ B °² [ ... Z] ] ]
```
With °<sup>i</sup> being either ':' or ','

#### Examples

## Examples

### Usage

### Tools
