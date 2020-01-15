// kompilować z opcjami -lrt -lm, tj. np. gcc ALL_01.c -lrt -lm
#include <time.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#define MAX 60000l
#define MLD 1000000000.0


/////////////////////////////////////////////
//   PROCEDURY POMOCNICZE                  //
/////////////////////////////////////////////
void utworz_MACIERZ(int n, int ***M){
// alokuje pamiÄÄ na tablicÄ rozmiaru nxn
// i wpisuje losowe wartoĹci 0/1 
int i,j;
    (*M) = (int **)malloc(n*sizeof(int *));
    for(i=0;i<n;i++){
        (*M)[i]=(int *)malloc(n*sizeof(int));
    }
    for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            (*M)[i][j]=rand()% 2;
            }
    }
}
/////////////////////////////////////////////
void utworz_MACIERZ_x(int n, int ***M, int x){
// alokuje pami na tablicÄ rozmiaru nxn
// i wpisuje wszdzie wartoĹci x
int i,j;
    (*M) = (int **)malloc(n*sizeof(int *));
    for(i=0;i<n;i++){
        (*M)[i]=(int *)malloc(n*sizeof(int));
    }
    for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            (*M)[i][j]=x;
            }
    }
}
/////////////////////////////////////////////
void wypisz_MACIERZ(int n, int **M){
// wypisuje wartoĹci tablicy
int i,j;

for(i=0;i<n;i++){
    for(j=0;j<n;j++)
        printf("%d",M[i][j]);
    printf("\n");
    }
}
/////////////////////////////////////////////
void zwolnij_MACIERZ(int n, int **M){
// zwalania pamiÄÄ zarezerwowanÄ dla macierzy
int i;
    for(i=0;i<n;i++)
    {
    free(M[i]);
    }
    free(M);
}
/////////////////////////////////////////////
//   ALGORYTM PIERWSZY                     //
/////////////////////////////////////////////
int ALGO_NAIWNY(int n, int **M){
int x1,y1,x2,y2,x,y;
int max=0;
int local_max=0;

for(x1=0;x1<n;x1++)
    for(y1=0;y1<n;y1++)
        for(x2=n-1;x2>x1-1;x2--)
            for(y2=n-1;y2>y1-1;y2--){
                local_max=0;
                for(x=x1;x<x2+1;x++)
                    for(y=y1;y<y2+1;y++)
                        local_max+=M[x][y];
                if ((local_max==(x2-x1+1)*(y2-y1+1)) && (local_max>max)) max=local_max;
                }
return max;
}
/////////////////////////////////////////////
//   ALGORYTM DRUGI                        //
/////////////////////////////////////////////
int REKURENCJA(int **M,int x1, int y1, int x2, int y2){
if ((x2==x1)&&(y2==y1)) return M[x1][y1];
    else if ((x2-x1)>(y2-y1))
        return REKURENCJA(M,x1,y1,(int)(x1+x2)/2,y2)*REKURENCJA(M,(int)(x1+x2+1)/2,y1,x2,y2);
            else return REKURENCJA(M,x1,y1,x2,(int)(y1+y2)/2)*REKURENCJA(M,x1,(int)(y1+y2+1)/2,x2,y2);
}
/////////////////////////////////////////////
int ALGO_REKURENCYJNY(int n, int **M){
int x1,y1,x2,y2;
int max=0;
int local_max;

for(x1=0;x1<n;x1++)
    for(y1=0;y1<n;y1++)
        for(x2=x1;x2<n;x2++)
            for(y2=y1;y2<n;y2++){
                local_max=REKURENCJA(M,x1,y1,x2,y2)*(x2-x1+1)*(y2-y1+1);
                if (local_max>max) max=local_max;
            }
return max;
}
/////////////////////////////////////////////
//   ALGORYTM TRZECI                       //
/////////////////////////////////////////////
int ALGO_DYNAMICZNY(int n, int **M){
int x1,x2,y;
int max=0;
int iloczyn;
int **MM;

utworz_MACIERZ_x(n,&MM,0);

for(y=0;y<n;y++)
    for(x1=0;x1<n;x1++){
        iloczyn=1;
        for(x2=x1;x2<n;x2++){
            iloczyn*=M[x2][y];
            MM[x1][x2]=iloczyn*(x2-x1+1+MM[x1][x2]);
            if (MM[x1][x2]>max) max=MM[x1][x2];
        }
    }
return max;
}
/////////////////////////////////////////////
//   ALGORYTM CZWARTY                      //
/////////////////////////////////////////////
int ALGO_CZULY(int n, int **M){
int x1,y1,x2,y2,ymax;
int max=0;
int local_max=0;

for(x1=0;x1<n;x1++)
    for(y1=0;y1<n;y1++){
        local_max=0;
        x2=x1;
        ymax=n-1;
        while ((x2<n)&&(M[x2][y1]==1)){
            y2=y1;
            while((y2<ymax+1)&&(M[x2][y2]==1)){
                y2++;
            }
            ymax=y2-1;
            local_max=(x2-x1+1)*(ymax-y1+1);
            if (local_max>max) max=local_max;
            x2++;
        }
    }
return max;
}
/////////////////////////////////////////////
//   POMIAR CZASU DLA WIELU PRÓB           //
/////////////////////////////////////////////
int main(){
     //wymiar macierzy/
    int **Macierz;
    srand(time(NULL));

  struct timespec tp0, tp1;
  double Tn,Fn,x;
  int n; // liczba testów
  
for(int n=14;n<=40;n+=2){

utworz_MACIERZ(n,&Macierz);
//utworz_MACIERZ_x(n,&Macierz,1);

clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);

ALGO_NAIWNY(n,Macierz); // Fn = log(n)
//ALGO_REKURENCYJNY(n,Macierz);
//ALGO_DYNAMICZNY(n,Macierz);
//ALGO_CZULY(n,Macierz);

clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);
zwolnij_MACIERZ(n,Macierz);

// zgadywana funkcja czasu
    Fn=n*n;
    //Fn=20000*n;
    //Fn=n*n*log(n);
    //Fn=n*n*sqrt(n);
    //Fn=n*n*log(n);
    //Fn=n*n/1000;
    //Fn=log(n)/15;

    ////////////////////////////////////////////////////////////////////////
    //    ODPOWIEDZI                                       
    // dla algorytmu naiwnego:       Fn = n^6      
    //
    // dla algorytmu rekurencyjnego: Fn = n^6      
    //
    // dla algorytmu dynamicznego:   Fn = n^3           
    //
    // dla algorytmu czulego:        Fn = n^4     dla macierzy jedynek
    //                               Fn = n^2     dla macierzy zer                
    ////////////////////////////////////////////////////////////////////////
    
  Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
  printf("n: %5d \tczas: %3.10lf \twspolczynnik: %3.5lf\n",n,Tn, Fn/Tn);
}
return 1;
}