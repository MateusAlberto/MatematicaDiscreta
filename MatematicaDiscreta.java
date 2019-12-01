import java.util.*;
import java.lang.Math;
/**
 * Classe que possui métodos estáticos explicativos
 * relacionados a alguns algoritmos de Matemática Discreta
 * @author Mateus Alberto Dorna de Oliveira Ferreira
 */
public class MatematicaDiscreta {
    public static void main(String[] args) throws Exception{
        List<String> mdc = invertivelModn(Integer.parseInt(args[0]), Integer.parseInt(args[1])/*, Integer.parseInt(args[2])*/);
        //List<String> mdc = ATP(Integer.parseInt(args[0]));
        for(int i = 0; i < mdc.size(); i++) {
            System.out.print(mdc.get(i));
        }
    }

    /**
     * Método de cálculo do MDC pelo Algoritmo de Euclides
     * @param a primeiro inteiro do MDC
     * @param b segundo inteiro do MDC
     * @return lista de strings contendo uma resposta explicativa do mdc
     */
    public static List<String> MDCEuclides(int a, int b) {
        List<String> resp = new ArrayList<String>();
        int mdc = 0;
        resp.add("mdc(" + a + "," + b + ") = ");
        
        //1º Passo - Trabalhar com a e b positivos, a > b
        a = Math.abs(a);
        b = Math.abs(b);
        if(a == b) {
            mdc = a;
        } else {
            if(a < b) {
                int aux = a;
                a = b;
                b = aux;
                resp.add("mdc(" + a + "," + b + ") = \n");
            }
            //Decompõe até o último resto
            int q = a / b;
            int r = a % b;
            resp.add("\n\t" + a + " = " + b + " * " + q + " + " + r + "\n");
            while (r > 0) {
                a = b;
                b = r;
                q = a / b;
                r = a % b;
                resp.add("\t" + a + " = " + b + " * " + q + " + " + r + "\n");
            }
            mdc = b;
        }
        resp.add("\n\tmdc = " + mdc + "\n");

        return resp;
    }
    
    /**
     * Método que exibe de forma explicativa uma combinação linear
     * de a e b usando o Algoritmo Estendido de Euclides
     * @param a primeiro inteiro da combinação linear
     * @param b segundo inteiro da combinação linear
     * @return lista de strings contendo uma resposta explicativa do AEE
     */
    public static List<String> combLinearAEE(int a, int b) {
        List<String> resp = new ArrayList<String>();
        List<Integer> As = new ArrayList<Integer>(); //lista dos primeiros divisores
        List<Integer> Bs = new ArrayList<Integer>(); //lista dos segundos divisores
        List<Integer> q = new ArrayList<Integer>(); //lista de quocientes
        List<Integer> r = new ArrayList<Integer>(); //lista de restos

        // ALGORITMO DE EUCLIDES

        int mdc = 0, i = 0;
        resp.add("AE: mdc(" + a + "," + b + ") = ");
        
        //1º Passo - Trabalhar com a e b positivos, a > b
        int A = Math.abs(a);
        int B = Math.abs(b);
        if(A == B) {
            mdc = A;
        } else {
            if(A < B) {
                int aux = A;
                A = B;
                B = aux;
                aux = a;
                a = b;
                b = aux;
                resp.add("mdc(" + A + "," + B + ") = \n");
            }
            //Adiciona às listas correspondentes
            As.add(A);
            Bs.add(B);
            //Decompõe até o último resto
            q.add((A / B));
            r.add((A % B));
            resp.add("\n\t" + As.get(i) + " = " + Bs.get(i) + " * " + q.get(i) + " + " + r.get(i) + "\n");
            while (r.get(i) > 0) {
                As.add(Bs.get(i));  //a = b;
                Bs.add(r.get(i));  //b = r;
                i++;
                q.add(As.get(i) / Bs.get(i));  //q = a / b;
                r.add(As.get(i) % Bs.get(i));  //r = a % b;
                
                resp.add("\t" + As.get(i) + " = " + Bs.get(i) + " * " + q.get(i) + " + " + r.get(i) + "\n");
            }
            mdc = Bs.get(i);
        }
        resp.add("\n\tmdc = " + mdc + "\n\n");
        
        //ALGORITMO ESTENDIDO DE EUCLIDES
        if(As.size() != 1){
            i--;
            int u = 1;
            int v = -1 * q.get(i);
            resp.add("AEE: \t" + mdc + " = " + As.get(i) + " * (" + u + ")" + " - " + Bs.get(i) + " * (" + v + ")\n");
            i--;
            while(i >= 0) {
                resp.add("\t" + mdc + " = " + Bs.get(i) + " * (" + u + ") + (" + As.get(i) + " - " + Bs.get(i) + " * (" + q.get(i) + ")) * (" + v + ")\n");
                int aux = u;
                u = v;
                v = aux + (q.get(i) * (-1 * v));
                resp.add("\t" + mdc + " = " + As.get(i) + " * (" + u + ") + " + Bs.get(i) + " * (" + v + ")\n");
                i--;
            }

            if(a < 0 && b < 0) {
                System.out.println("1\t" + a + "\t" + b);
                u *= -1;
                v *= -1;
                resp.add("Ajustando:\n");
                resp.add("\t" + mdc + " = " + -A + " * (" + u + ") - " + B + " * (" + v + ")\n\n");
            } else {
                if(a < 0 && b > 0) {
                    System.out.println("1");
                    u *= -1;
                    resp.add("Ajustando:\n");
                    resp.add("\t" + mdc + " = " + -A + " * (" + u + ") + " + B + " * (" + v + ")\n\n");
                } else {
                    if(b < 0 && a > 0) {
                        System.out.println("1");
                        v *= -1;
                        resp.add("Ajustando:\n");
                        resp.add("\t" + mdc + " = " + A + " * (" + u + ") - " + B + " * (" + v + ")\n\n");
                    }
                }
            }
            resp.add("\talfa = " + u + "\tbeta = " + v + "\n");
        }

        return resp;
    }

    /**
     * Método que exibe de forma explicativa a solução da edl
     * do tipo ax + by = c
     * @param a primeiro inteiro da edl
     * @param b segundo inteiro da edl
     * @param c resultado da edl
     * @return lista de strings contendo uma resposta explicativa do AEE
     */
    public static List<String> EDL(int a, int b, int c) {
        List<String> resp = new ArrayList<String>();
        List<Integer> As = new ArrayList<Integer>(); //lista dos primeiros divisores
        List<Integer> Bs = new ArrayList<Integer>(); //lista dos segundos divisores
        List<Integer> q = new ArrayList<Integer>(); //lista de quocientes
        List<Integer> r = new ArrayList<Integer>(); //lista de restos

        // ALGORITMO DE EUCLIDES

        int mdc = 0, i = 0;
        resp.add("1. AE: mdc(" + a + "," + b + ") = ");
        
        //1º Passo - Trabalhar com a e b positivos, a > b
        int A = Math.abs(a);
        int B = Math.abs(b);
        if(A == B) {
            mdc = A;
        } else {
            if(A < B) {
                int aux = A;
                A = B;
                B = aux;
                aux = a;
                a = b;
                b = aux;
                resp.add("mdc(" + A + "," + B + ") = \n");
            }
            //Adiciona às listas correspondentes
            As.add(A);
            Bs.add(B);
            //Decompõe até o último resto
            q.add((A / B));
            r.add((A % B));
            resp.add("\n\t" + As.get(i) + " = " + Bs.get(i) + " * " + q.get(i) + " + " + r.get(i) + "\n");
            while (r.get(i) > 0) {
                As.add(Bs.get(i));  //a = b;
                Bs.add(r.get(i));  //b = r;
                i++;
                q.add(As.get(i) / Bs.get(i));  //q = a / b;
                r.add(As.get(i) % Bs.get(i));  //r = a % b;
                
                resp.add("\t" + As.get(i) + " = " + Bs.get(i) + " * " + q.get(i) + " + " + r.get(i) + "\n");
            }
            mdc = Bs.get(i);
        }
        resp.add("\n\tmdc = " + mdc + "\n\n");
        
        //Verificação se a edl admite solução
        if(c % mdc == 0) {
            int fator = c / mdc;
            resp.add("\t" + mdc + " divide " + c + " pois " + c + " = " + mdc + " * " + fator + "\n\n");
            //ALGORITMO ESTENDIDO DE EUCLIDES
            if(As.size() != 1){
                i--;
                int u = 1;
                int v = -1 * q.get(i);
                resp.add("2. AEE: " + mdc + " = " + As.get(i) + " * (" + u + ")" + " - " + Bs.get(i) + " * (" + v + ")\n");
                i--;
                while(i >= 0) {
                    resp.add("\t" + mdc + " = " + Bs.get(i) + " * (" + u + ") + (" + As.get(i) + " - " + Bs.get(i) + " * (" + q.get(i) + ")) * (" + v + ")\n");
                    int aux = u;
                    u = v;
                    v = aux + (q.get(i) * (-1 * v));
                    resp.add("\t" + mdc + " = " + As.get(i) + " * (" + u + ") + " + Bs.get(i) + " * (" + v + ")\n");
                    i--;
                }

                if(a < 0 && b < 0) {
                    u *= -1;
                    v *= -1;
                    int x0 = u * fator;
                    int y0 = v * fator;
                    resp.add("Ajustando:\n");
                    resp.add("\t" + mdc + " = " + -A + " * (" + u + ") - " + B + " * (" + v + ")\n\n");
                    //3º Passo: Exibindo uma Solução da E.D.L -> tive que colocar nos três lados para ajeitar à string final
                    resp.add("\n3. Multiplicando a combinação linear acima por um fator adequado, no caso, " + fator + "\n\n");
                    resp.add("\t" + mdc + " * " + fator + " = " + -A + " * (" + u + " * " + fator +  ") - " + B + " * (" + v + " * " + fator + ")\n\n");
                    resp.add("\t" + (mdc * fator) + " = " + -A + " * (" + x0 +  ") - " + B + " * (" + y0 + ")\n\n");

                    resp.add("\t(x0,y0) = (" + x0 + "," + y0 + ") é uma solução da e.d.l.: " + -A + "x - " + B + "y = " + c + "\n\n");
                    resp.add("4. Solução Geral:\n\n");
                    resp.add("\tx = " + x0 + " + (" + b  + ")/" + mdc + "t\ty = " + y0 + " - (" + a + ")/" + mdc + "t, tal que t é um número inteiro\n\n");
                    resp.add("\tx = " + x0 + " - " + (B / mdc) + "t\t\ty = " + y0 + " + " + (A / mdc) + "t\n");
                } else {
                    if(a < 0 && b > 0) {
                        u *= -1;
                        int x0 = u * fator;
                        int y0 = v * fator;
                        resp.add("Ajustando:\n");
                        resp.add("\t" + mdc + " = " + -A + " * (" + u + ") + " + B + " * (" + v + ")\n\n");
                        //3º Passo: Exibindo uma Solução da E.D.L -> tive que colocar nos três lados para ajeitar à string final
                        resp.add("\n3. Multiplicando a combinação linear acima por um fator adequado, no caso, " + fator + "\n\n");
                        resp.add("\t" + mdc + " * " + fator + " = " + -A + " * (" + u + " * " + fator +  ") - " + B + " * (" + v + " * " + fator + ")\n\n");
                        resp.add("\t" + (mdc * fator) + " = " + -A + " * (" + (u * fator) +  ") - " + B + " * (" + (v * fator) + ")\n\n");

                        resp.add("\t(x0,y0) = (" + x0 + "," + y0 + ") é uma solução da e.d.l.: " + -A + "x + " + B + "y = " + c + "\n\n");
                        resp.add("4. Solução Geral:\n\n");
                        resp.add("\tx = " + x0 + " + (" + b  + ")/" + mdc + "t\ty = " + y0 + " - (" + a + ")/" + mdc + "t, tal que t é um número inteiro\n\n");
                        resp.add("\tx = " + x0 + " + " + (B / mdc) + "t\t\ty = " + y0 + " + " + (A / mdc) + "t\n");
                    } else {
                        if(b < 0 && a > 0) {
                            v *= -1;
                            int x0 = u * fator;
                            int y0 = v * fator;
                            resp.add("Ajustando:\n");
                            resp.add("\t" + mdc + " = " + A + " * (" + u + ") - " + B + " * (" + v + ")\n\n");
                            //3º Passo: Exibindo uma Solução da E.D.L -> tive que colocar nos três lados para ajeitar à string final
                            resp.add("\n3. Multiplicando a combinação linear acima por um fator adequado, no caso, " + fator + "\n\n");
                            resp.add("\t" + mdc + " * " + fator + " = " + A + " * (" + u + " * " + fator +  ") - " + B + " * (" + v + " * " + fator + ")\n\n");
                            resp.add("\t" + (mdc * fator) + " = " + A + " * (" + (u * fator) +  ") - " + B + " * (" + (v * fator) + ")\n\n");

                            resp.add("\t(x0,y0) = (" + x0 + "," + y0 + ") é uma solução da e.d.l.: " + A + "x - " + -B + "y = " + c + "\n\n");
                            resp.add("4. Solução Geral:\n\n");
                            resp.add("\tx = " + x0 + " + (" + b  + ")/" + mdc + "t\ty = " + y0 + " - (" + a + ")/" + mdc + "t, tal que t é um número inteiro\n\n");
                            resp.add("\tx = " + x0 + " - " + (B / mdc) + "t\t\ty = " + y0 + " - " + (A / mdc) + "t\n");
                        } else {
                            int x0 = u * fator;
                            int y0 = v * fator; 
                            //3º Passo: Exibindo uma Solução da E.D.L -> tive que colocar nos três lados para ajeitar à string final
                            resp.add("\n3. Multiplicando a combinação linear acima por um fator adequado, no caso, " + fator + "\n\n");
                            resp.add("\t" + mdc + " * " + fator + " = " + A + " * (" + u + " * " + fator +  ") + " + B + " * (" + v + " * " + fator + ")\n\n");
                            resp.add("\t" + (mdc * fator) + " = " + A + " * (" + (u * fator) +  ") + " + B + " * (" + (v * fator) + ")\n\n");

                            resp.add("\t(x0,y0) = (" + x0 + "," + y0 + ") é uma solução da e.d.l.: " + A + "x + " + B + "y = " + c + "\n\n");
                            resp.add("4. Solução Geral:\n\n");
                            resp.add("\tx = " + x0 + " + (" + b  + ")/" + mdc + "t\ty = " + y0 + " - (" + a + ")/" + mdc + "t, tal que t é um número inteiro\n\n");
                            resp.add("\tx = " + x0 + " + " + (B / mdc) + "t\t\ty = " + y0 + " - " + (A / mdc) + "t\n");
                        }
                    }
                }
            }
        } else {
            resp.add("A a e.d.l. " + a + "x + " + b + "y = " + c + " não admite solução porque\n\n\t\t" + mdc + " não divide " + c + "\n");
        }

        return resp;
    }
    

    /**
     * Implementação do Algoritmo Testa Primalidade - Diz se um inteiro dado é primo ou não
     * @param n inteiro a ter a primalidade testada
     * @return vetor de strings com a resposta explicativa da resolução
     */
    public static List<String> ATP(int n) {
        List<String> resp = new ArrayList<String>();
        List<Integer> dividem = new ArrayList<Integer>();
        if(n >= 2) {
            int raizN = (int)Math.sqrt(n);
            resp.add("Raiz de " + n + " = " + raizN + "\n");
            resp.add("Pelo crivo de Erastótenes:\n");
            List<Integer> primos = crivoErastotenes(raizN);
            for(int i = 0; i < primos.size(); i++) {
                if(n % primos.get(i) == 0) {
                    resp.add("\t" + primos.get(i) + " divide " + n + "\n");
                    dividem.add(primos.get(i));
                    i = primos.size();
                } else {
                    resp.add("\t" + primos.get(i) + " não divide " + n + "\n");
                }
            }
        } else {
            resp.add("O valor de entrada deve ser maior ou igual a 2");
        }
        if(dividem.size() == 0) {
            resp.add(n + " é primo\n");
        } else {
            resp.add(n + " não é primo\n");
        }
        return resp;
    }

    /**
     * Algoritmo que monta uma lista de inteiros primos menores que o argumento m
     * @param m inteiro m que é o limite superior da lista de primos
     * @return lista de primos abaixo de m
     */
    public static List<Integer> crivoErastotenes(int m) {
        List<Integer> primos = new ArrayList<Integer>();
        if(m >= 2) {
            primos.add(2); //Primeiro primo é o 2
            //Primeira Etapa: listar todos os ímpares de 3 até m
            List<Integer> aux = new ArrayList<Integer>();
            for(int i = 3; i <= m; i+=2) {
                aux.add(i);
            }

            //Segunda Etapa: eliminar os múltiplos dos ímpares até m
            for(int i = 3; i <= m; i += 2) {
                for(int j = 0; j < aux.size(); j++) {
                    if(aux.get(j) != i && aux.get(j) % i == 0) {
                        aux.remove(j);
                        j--;
                    }
                }
            }

            //Dar saída
            for(int i = 0; i < aux.size(); i++) {
                primos.add(aux.get(i));
            }
        }

        return primos;
    }


    /**
     * Método que exibe de forma explicativa a verificação se
     * o inteiro a é invertível módulo n
     * @param a número inteiro que será visto se é invertível módulo n
     * @param n módulo da operação
     * @return lista de strings contendo uma resposta explicativa do AEE
     */
    public static List<String> invertivelModn(int a, int n) {
        List<String> resp = new ArrayList<String>();
        List<Integer> As = new ArrayList<Integer>(); //lista dos primeiros divisores
        List<Integer> Bs = new ArrayList<Integer>(); //lista dos segundos divisores
        List<Integer> q = new ArrayList<Integer>(); //lista de quocientes
        List<Integer> r = new ArrayList<Integer>(); //lista de restos

        // ALGORITMO DE EUCLIDES

        int mdc = 0, i = 0;
        resp.add("AE: mdc(" + a + "," + n + ") = ");
        
        //1º Passo - Trabalhar com a e b positivos, a > b
        int A = Math.abs(n);
        int B = Math.abs(a);
        if(A == B) {
            mdc = A;
        } else {
            if(A < B) {
                int aux = A;
                A = B;
                B = aux;
                /*aux = a;
                a = b;
                b = aux;*/
                resp.add("mdc(" + A + "," + B + ") = \n");
            }
            //Adiciona às listas correspondentes
            As.add(A);
            Bs.add(B);
            //Decompõe até o último resto
            q.add((A / B));
            r.add((A % B));
            resp.add("\n\t" + As.get(i) + " = " + Bs.get(i) + " * " + q.get(i) + " + " + r.get(i) + "\n");
            while (r.get(i) > 0) {
                As.add(Bs.get(i));  //a = b;
                Bs.add(r.get(i));  //b = r;
                i++;
                q.add(As.get(i) / Bs.get(i));  //q = a / b;
                r.add(As.get(i) % Bs.get(i));  //r = a % b;
                
                resp.add("\t" + As.get(i) + " = " + Bs.get(i) + " * " + q.get(i) + " + " + r.get(i) + "\n");
            }
            mdc = Bs.get(i);
        }
        resp.add("\n\tmdc = " + mdc + "\n\n");
        
        //Verificação se o mdc(n, a) = 1
        if(mdc == 1) {
            resp.add( a + " é invertível módulo " + n + "\n");
        } else {
            resp.add( a + " não é invertível módulo " + n + "\n");
        }

        return resp;
    }
    
}