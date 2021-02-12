package com.mmfinfotech.NavneetTiwaritest.utils;

public class IsNumberIsSimple {
       
        public void main(String args[]) {
        
            for ( int i=0; i < 100; i++) {
              isNumberIsSimple(i);
            }     
        }   
        
        public void isNumberIsSimple(int num){
        	for ( int i=2; i < num; i++) {
                  if ( num%i == 0) {
                          return;
                  }      
          	}
        }
}