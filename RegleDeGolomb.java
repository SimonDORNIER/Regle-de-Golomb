/*
  Projet d Algorithmique et de Programation Orientee Objet
  Recherche d une regle de Golomb de longueur N et K graduations
  Par ADRIEN Aymeric et DORNIER Simon
*/
package Golomb;
import java.util.*;


public class RegleDeGolomb
{
  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);
    ////////////////////////////////////////////////////////////////////////////
    //pour poursuivre le traitement                                           //
    boolean TraitementEnCours = true;                                         //
    //pour signaler si on a trouve un resultat                                //
    boolean ResultatTrouve = false;                                           //
    //N pour la longueur de la serie                                          //
    System.out.print("Entrez la longueur de la série: ");                     //
    int N = sc.nextInt();                                                     //
    //K pour le nombre de graduations                                         //
    System.out.print("Entrez le nombre de graduations: ");                    //
    int K = sc.nextInt();                                                     //
    //tableau pour ranger la serie generee                                    //
    int serie[] = new int[K];                                                 //
    //liste de nombre avec pour taille N                                      //
    //afin de tirer un nombre aleatoire unique compris entre 1 et N-1         //
    ArrayList<Integer> tabNombre = new ArrayList<>();                         //
    //liste pour ranger les distances calcule                                 //
    ArrayList<Integer> TabdeDiff = new ArrayList<>();                         //
    //initialise la premiere et derniere valeur de la serie                   //
    //car par defaut la serie comprend 0 et N                                 //
    serie[0] = 0;                                                             //
    serie[K-1] = N;                                                           //
    ////////////////////////////////////////////////////////////////////////////

    //test pour empecher une saisie erronee
    if (K>=2 && K<N)
    {
      //tant qu on a trouve aucun resultat
      while(!ResultatTrouve)
      {
        //rempli une arraylist pour le tirage alea unique
        for(int i = 1; i<N; i++)
        {
              tabNombre.add(i);
        }

        //on genere de facon aleatoire une serie de nombre
        //avec tirage unique a l aide du tab precedent
        for(int i=1; i<K-1; i++)
        {
          int tmpRand = (int)(Math.random() * (tabNombre.size() - 1));
          serie[i] = tabNombre.get(tmpRand);
          tabNombre.remove(tmpRand);
        }

        //on trie la serie genere juste avant
        Arrays.sort(serie);

        //on calcule les distance entre chaque
        //graduations de la serie
        //qu on range dans une arraylist
        for (int i = 0; i < K; i++)
        {
          for (int j = i+1; j<K ; j++)
          {
            TabdeDiff.add(serie[j] - serie[i]);
          }
        }

        //on trie la list de diff (pour optimiser le temps  de comparaison)
        Collections.sort(TabdeDiff);

        // verifier si les valeurs des distances sont toutes uniques
        //et nous renvoie un boolean si solution trouvee ou non
        ResultatTrouve = verif(TabdeDiff, TraitementEnCours);

        //on reset pour un eventuel nouveau traitement
        TraitementEnCours = true;
        TabdeDiff.clear();

      }//fin while aucun resultat trouve

      System.out.print("Série trouvée: ");
      printSerie(serie);
    }//fin if de la bonne saisie
  }//fin main

  //affiche le contenu d un tableau donne
  public static void printSerie(int tab[])
  {
    System.out.print("[ ");
    for(int i=0; i<tab.length; i++)
      System.out.print(tab[i]+" ");
    System.out.println("]");
  }//fin methode printSerie

  //verifie les distances voisines deux par deux dans la liste triee
  //et si elle trouve deux distances identique  alors elle renvoie faux
  public static boolean verif(ArrayList< Integer > L, boolean Traitement)
  {
    boolean Resultat = false;
    int z = 0;
    //le while tourne tant qu il a pas trouve d erreur
    //ou qu il n a pas atteint la fin de la liste de distance
    while(Traitement && z<L.size()-2)
    {
      if(L.get(z) == L.get(z+1))
      {
        //si on a trouve deux distances identiques
        //on passe traitement a faux pour sortir de la boucle
        //et on passe le resultat en non trouve
        Traitement = false;
        Resultat = false;
      }
      z++;
    }

    //passe le boolean a vrai si il n'a pas trouve deux distances identiques
    if (Traitement)
    {
      Resultat = true;
    }
    return Resultat;
  }//fin methode verif

}//fin de classe
