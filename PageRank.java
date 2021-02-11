package ir.lab.assignment;

import java.util.*;
import java.io.*;
public class PageRank 
{
	public int path[][] = new int[10][10];
	public double pagerank[] = new double[10];
	public static void main(String []args) 
	{
		int nodes, i, j, cost;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the Number of WebPages : ");
		nodes = in.nextInt();
		PageRank p = new PageRank();
		System.out.println("Enter the Adjacency Matrix (1 if the Nodes are connected and 0 is the Nodes are not connected) : ");
		for (i = 1; i <= nodes; i++)
		{
			for (j = 1; j <= nodes; j++) 
			{
				System.out.print("Node "+i+" to Node "+j+" : ");
				p.path[i][j] = in.nextInt();
				if (j == i)
				{
					p.path[i][j] = 0;
				}					
			}
		}
		System.out.println("\nThe Enteterd Graph : ");
		for (i = 1; i <= nodes; i++)
		{
			System.out.print("Node "+i+" --> ");
			for (j = 1; j <= nodes; j++) 
			{
				System.out.print(p.path[i][j]+" ");					
			}
			System.out.println();
		}
		System.out.println("Note : Same Node to Same Node will always be '0'");
		p.calc(nodes);
	}
	public void calc(double totalNodes) 
	{	
		double InitialPageRank;
		double OutgoingLinks = 0;
		double DampingFactor = 0.85;
		double TempPageRank[] = new double[10];
		int ExternalNodeNumber;
		int InternalNodeNumber;
		int k = 1;
		int ITERATION_STEP = 1;
		InitialPageRank = 1 / totalNodes;
		System.out.println("\nTotal Number of Nodes :" + totalNodes + "\nInitial PageRank of All Nodes : "+InitialPageRank);

		for (k = 1; k <= totalNodes; k++) 
		{
			this.pagerank[k] = InitialPageRank;
		}
		
		System.out.println("Initial PageRank Values of Each Node : ");
		for (k = 1; k <= totalNodes; k++) 
		{
			System.out.println("Page Rank of Node "+k+ " : " + this.pagerank[k]);
		}
		
		while (ITERATION_STEP <= 2) 
		{ 
			for (k = 1; k <= totalNodes; k++)
			{
				TempPageRank[k] = this.pagerank[k];
				this.pagerank[k] = 0;
			}
			for (InternalNodeNumber = 1; InternalNodeNumber <= totalNodes; InternalNodeNumber++) 
			{
				for (ExternalNodeNumber = 1; ExternalNodeNumber <= totalNodes; ExternalNodeNumber++)
				{
					if (this.path[ExternalNodeNumber][InternalNodeNumber] == 1) 
					{
						k = 1;
						OutgoingLinks = 0;
						while (k <= totalNodes) 
						{
							if (this.path[ExternalNodeNumber][k] == 1) 
							{
								OutgoingLinks = OutgoingLinks + 1;
							}
							k = k + 1;
						}    
						this.pagerank[InternalNodeNumber] += TempPageRank[ExternalNodeNumber] * (1 / OutgoingLinks);
					}
				}
			}
			System.out.println("\nPage Ranks After Step - "+ITERATION_STEP);
			for (k = 1; k <= totalNodes; k++)
			{
				System.out.println("Page Rank of Node "+k+ " : " + this.pagerank[k]);
			}
			ITERATION_STEP = ITERATION_STEP + 1;
		}
		for (k = 1; k <= totalNodes; k++) 
		{
			this.pagerank[k] = (1 - DampingFactor) + DampingFactor * this.pagerank[k];
		}
		System.out.println("\nThe Final Page Ranks : ");
		for (k = 1; k <= totalNodes; k++) 
		{
			System.out.println("Page Rank of Node "+k+ " : " + this.pagerank[k]);
		}
		
	}
}