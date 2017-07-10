{\rtf1\ansi\ansicpg1252\cocoartf1404\cocoasubrtf460
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 import util.*;\
\
public class Josephus\
\{\
private int nextind( int size, int current)\
\{ if(current<size-1)\
return current+1;\
else \
return 0;\}\
\
public Queue<Integer> buildQueue(int M, int N)\
\{\
Integer[] circle= new Integer[N];\
for(int i=0; i<circle.length;i++)\
\{circle[i]=i;\}\
Queue<Integer> q= new Queue<>();\
int i=0;\
int proceed=0;\
int eliminate=M-1;\
\
while(q.size()<N)\
\{if(proceed<eliminate)\
proceed++;\
else\
\{if(circle[i]==null)\
i=nextind(circle.length, i);\
q.enqueue(circle[i]);\
circle[i]=null;\
proceed=0;\
\}\
i=nextind(circle.length,i);\
\}\
return q;\
\}\
public static void main(String[] args)\
\{\
Queue<Integer> q=buildQueue(Integer.parseInt(args[0], Integer.parseInt(args[1]));\
System.out.println(q);\
\}\
\}\
\
\
\
\
\
\
\
\
\
\
\}}