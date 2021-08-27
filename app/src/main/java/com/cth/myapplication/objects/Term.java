package com.cth.myapplication.objects;

//Enum for Term
public enum Term {
    None,Fall, Winter,FallWinter,Summer,FallSummer,WinterSummer,FallWinterSummer;
    private final int value;    // Bit value for each Term

    Term(){
        this.value = this.ordinal();
    }//Term

    public boolean checkTerm(Term term){
        return (this.getValue() & term.getValue()) == term.getValue();
    }//checkSemester

    public boolean isFall(){
        return (this.getValue() & Term.Fall.getValue()) == Term.Fall.getValue();
    }//isFall

    public boolean isWinter(){
        return (this.getValue() & Term.Winter.getValue()) == Term.Winter.getValue();
    }//isWinter

    public boolean isSummer(){
        return (this.getValue() & Term.Summer.getValue()) == Term.Summer.getValue();
    }//isSummer

    public int getValue() {
        return value;
    }//getValue

    @Override
    public String toString() {
        return this.name();
    }

    public static Term getTerm(int x) {
        return x >= 0 && x < 8 ? Term.values()[x] :null;
    }//getInteger
}//Term
