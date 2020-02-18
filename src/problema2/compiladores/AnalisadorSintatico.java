/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema2.compiladores;

import java.util.ArrayList;

/**
 *
 * @author Henrique
 */
public class AnalisadorSintatico {
   private ArrayList<Tokens> codigoTratado;
   private Tokens atual;
   private Tokens anterior;
   private Tokens proximo;
   private int indice =0;
   private ArrayList<String> Tipo;
   
   public AnalisadorSintatico(Tokens atual, Tokens anterior, Tokens proximo){
       this.atual= atual;
       this.anterior= anterior;
       this.proximo= proximo;
       
   }

   public AnalisadorSintatico(ArrayList<Tokens> codigoTratado){
       this.codigoTratado = codigoTratado;
   }
   
   public ArrayList<String> AnalisePrograma (){
       atual= codigoTratado.get(indice);
       proximo= codigoTratado.get(indice+1);
       if((atual.getLexema()).toString()=="const"){
           AnaliseConst();
       } else if((atual.getLexema()).toString()=="START"){
           AnaliseStart();
       }else if((atual.getLexema()).toString()=="var"){
           AnaliseVariavel();
       }else if((atual.getLexema()).toString()=="struct"){
           AnaliseStruct();
       }else if((atual.getLexema()).toString()=="typedef"){
           AnaliseTypedef();
       }
       return null;
       
   }
    /**
     * @return the atual
     */
    public Tokens getAtual() {
        return atual;
    }

    /**
     * @param atual the atual to set
     */
    public void setAtual(Tokens atual) {
        this.atual = atual;
    }

    /**
     * @return the anterior
     */
    public Tokens getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(Tokens anterior) {
        this.anterior = anterior;
    }

    /**
     * @return the proximo
     */
    public Tokens getProximo() {
        return proximo;
    }

    /**
     * @param proximo the proximo to set
     */
    public void setProximo(Tokens proximo) {
        this.proximo = proximo;
    }

    private void AnaliseConst() {
        if(proximo.getTipo()=="DELIMITADOR" && proximo.getLexema().toString() == "{"){
            andaUm();
            while(atual.getTipo()!="DELIMITADOR" && atual.getLexema().toString()!= "}"){
                andaUm();
                ConstantStructure();
            }
                
        }
    }
    
    private void ConstantStructure(){
        if(Tipo.contains(atual.getLexema().toString())){
            andaUm();
            if(atual.getTipo() == "IDENTIFICADOR" && proximo.getLexema().toString() == "="){
                andaUm();
                
                if(proximo.getTipo() == "cadeiaDeCaracteres" || proximo.getTipo()
                        == "numeros" || proximo.getTipo() == "boolean"){
                   andaUm();
                    if(proximo.getLexema().toString() == ";"){
                        
                    }
                }
            }
        }
    }

    private void AnaliseStart() {
         if(proximo.getTipo()=="DELIMITADOR" && proximo.getLexema().toString() == "("){
           andaUm();
            if(proximo.getTipo() == "DELIMITADOR" && proximo.getLexema().toString() == ")"){
                andaUm();
                if(proximo.getTipo()=="DELIMITADOR" && proximo.getLexema().toString() == "{"){
                    
                    while(atual.getTipo()!="DELIMITADOR" && atual.getLexema().toString()!= "}"){
                       andaUm();
                    }
            }
         }
           
                
        }
    }
    private void andaUm(){
        indice++;
        anterior=atual;
        atual=proximo;
        proximo=codigoTratado.get(indice+1);
    }

    private void AnaliseVariavel() {
         //To change body of generated methods, choose Tools | Templates.
           if(proximo.getTipo()=="DELIMITADOR" && proximo.getLexema().toString() == "{")
        {
            while(atual.getTipo()!="DELIMITADOR" && atual.getLexema().toString()!= "}"){
                andaUm();
                VarV();
            }
                
        }
    }
    private void VarV() {
        //To change body of generated methods, choose Tools | Templates.
        if(Tipo.contains(atual.getLexema().toString())){
            andaUm();
            complementV();
            VarV();
        }
    }

    private void complementV() {
        //To change body of generated methods, choose Tools | Templates.
        if(atual.getTipo()=="IDENTIFICADOR" ){
            if(proximo.getLexema().toString()==","){
                andaUm();
                varEqType();
            }else if(proximo.getLexema().toString()=="="){
                andaUm();
                if(proximo.getTipo() == "cadeiaDeCaracteres" || proximo.getTipo()
                       == "numeros" || proximo.getTipo() == "boolean"){
                  andaUm();
                   if(proximo.getLexema().toString() == ";"){

                   }
               }
            }
        }
         
    }

    private void varEqType() {
        //To change body of generated methods, choose Tools | Templates.
        complementV();
        if(proximo.getLexema().toString()==";"){
            
        }
        
    }

    private void AnaliseStruct() {
         //To change body of generated methods, choose Tools | Templates.
           if(proximo.getTipo()=="DELIMITADOR" && proximo.getLexema().toString() == "{")
        {
            andaUm();
            while(atual.getTipo()!="DELIMITADOR" && atual.getLexema().toString()!= "}"){
                andaUm();
                 if((atual.getLexema()).toString()=="struct"){
                     andaUm();
                     if(atual.getTipo() == "IDENTIFICADOR" ){
                         andaUm();
                         Extends();
                         if(atual.getLexema().toString()=="{"){
                             attributes();
                             if(atual.getLexema().toString()=="}"){
                                 andaUm();
                                 if(atual.getLexema().toString()==";"){
                                     return;
                                 }
                             }
                        }
                         
                     }
                }
                
            }
                
        }
    }
     private void Extends() {
         if((atual.getLexema()).toString()=="extends"){
             andaUm();
             if(atual.getTipo()=="IDENTIFICADOR"){
                 andaUm();
                 return;
             }
             
         }
         
    }

    private void attributes() {
        if(Tipo.contains(atual.getLexema().toString())){
            andaUm();
            if(atual.getTipo()=="IDENTIFICADOR"){
                andaUm();
                if(atual.getLexema().toString()==";"){
                    andaUm();
                    attributes();
                }
            }
        }
    }   
        
        
    

    private void AnaliseTypedef() {
         //To change body of generated methods, choose Tools | Templates.
           if(proximo.getTipo()=="DELIMITADOR" && proximo.getLexema().toString() == "{")
               andaUm();
        {
            while(atual.getTipo()!="DELIMITADOR" && atual.getLexema().toString()!= "}"){
                typedef();
                
            }
                
        }
    }

    private void typedef() {
         //To change body of generated methods, choose Tools | Templates.
         if((atual.getLexema()).toString()=="typedef"){
             andaUm();
             if((atual.getLexema()).toString()=="struct"){
                 andaUm();
                 if(atual.getTipo()=="IDENTIFICADOR"){
                     andaUm();
                     if(atual.getLexema().toString()==";"){
                         andaUm();
                         typedef();
                         return;
                     }
                 }
             }
         }
    }
    
    private void Incremments(){
        if(atual.getTipo()=="IDENTIFICADOR"){
            andaUm();
            incremment();
            andaUm();
            if(atual.getLexema().toString()==";"){
                return;
            }
        }
    }

    private void incremment() {
        //To change body of generated methods, choose Tools | Templates.
        if(atual.getLexema().toString()=="++"||atual.getLexema().toString()=="--"){
            return;
        }
    }
    private void chFunProc(){
        if(atual.getTipo()=="IDENTIFICADOR"){
            andaUm();
            if(atual.getLexema().toString()=="("){
                andaUm();
                chParam();
                andaUm();
                if(atual.getLexema().toString()==")"){
                    andaUm();
                    if(atual.getLexema().toString() == ";"){
                        return;
                    }
                }
                    
            }
        }
        
    }

    private void chParam() {
        //To change body of generated methods, choose Tools | Templates.
        if(atual.getTipo()=="IDENTIFICADOR"){
            andaUm();
            chParam2();
            return;
        }
        else if(atual.getTipo() == "cadeiaDeCaracteres" || atual.getTipo()
                        == "numeros" || atual.getTipo() == "boolean"){
            andaUm();
            chParam2();
            return;
        }
    }

    private void chParam2() {
         //To change body of generated methods, choose Tools | Templates.
         if(atual.getLexema().toString() == ","){
             andaUm();
             if(atual.getTipo()=="IDENTIFICADOR"){
                 andaUm();
                 chParam2();
             }
             else if(atual.getTipo() == "cadeiaDeCaracteres" || atual.getTipo()
                        == "numeros" || atual.getTipo() == "boolean"){
                 
                 andaUm();
                 chParam2();
             }
         }
    }

   

}
