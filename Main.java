package SYMiniProject;
//imported Scanner and LinkedList class from Collections Framework
import java.util.Scanner;
import java.util.LinkedList;

//Class Node
class Node {
	String dishName;
	int time;
	double price;
	Node left,right;


	Node(String d,int t,double p) {
		// Constructor to initialize data of Node i.e. Binary Tree Node
		this.left = null;
		this.right = null;
		dishName = d;
		this.time = t;
		this.price = p;
	}
}
//Class LLNode
class LLNode{
	String name,contactNo;
	LinkedList orders;
	double totalBill;
	LLNode next;
	LLNode(String n,double b,String phN,LinkedList ord){
		// Constructor to initialize data of LLNode i.e. Database Node
		this.name = n;
		this.totalBill = b;
		this.contactNo = phN;
		this.orders = ord;
		this.next = null;
	}
}
//Linked List class to store Database of hotel
class LL {
	LLNode head;

	// insert Node in LL Database
	public void insert(LLNode n) {
		if (head == null) {
			head = n;
		}
		else {
			LLNode temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = n;
		}
	}

	// display all data stored in hotel database
	public void displayDataOfAll(){
		LLNode temp = head;
		if(head==null){
			System.out.println("No Orders received!!!");
		}
		else {
			while (temp != null) {
				double total_p = 0;
				System.out.println("-----------------------------------");
				System.out.println(temp.name);
				System.out.println(temp.contactNo);
				System.out.println();
				for (int i = 0; i < temp.orders.size(); i++) {
					Node curr_dish = (Node) temp.orders.get(i);
					System.out.println(curr_dish.dishName + " : Rs." + curr_dish.price);
				}
				System.out.println();
				System.out.println("TOTAL BILL WITH TAX : Rs." + temp.totalBill);
				System.out.println("-----------------------------------");
				System.out.println();
				temp = temp.next;
			}
		}
	}

}

//Class NodeP
class NodeP {
	String name,contactNo;
	int time;
	LinkedList orders=new LinkedList();
	NodeP next;

	NodeP(String name, String cn,int t,LinkedList ll){
// Constructor to initialize Node in Priority Queue
		this.name=name;
		this.contactNo=cn;
		this.orders = ll;
		this.time=t;
		this.next=null;
	}
}

//Class Priority Queue
class PQueue{
	NodeP front,rear;
	// enqueue in Priority Queue, time is set as priority
	void Enque(NodeP n) {
		if(front==null && rear==null) {
			front=n;
			rear=n;

		}
		else {
			NodeP temp=front;
			NodeP  curr=front;
			while(temp!=null &&  temp.time<n.time) {
				curr=temp;
				temp=temp.next;

			}
			if(temp==front){
				n.next = front;
				front = n;
			}
			else{
				n.next=temp;
				curr.next=n;
			}
		}
	}
	// Display all orders in Priority Queue
	void Display() {
		NodeP cust=front;
		if(front==null && rear==null) {
			System.out.println("No Orders Remaining!!!");
		}
		else {
			while(cust!=null) {
				System.out.println("--------------------------------");
				System.out.println("Name: "+cust.name);
				System.out.println("Contact number: "+cust.contactNo);
				for(int i=0;i<cust.orders.size();i++) {
					Node curr = (Node) cust.orders.get(i);
					System.out.println(curr.dishName + " : Rs." +curr.price);
				}
                 cust=cust.next;
				System.out.println("--------------------------------");
			}
		}
	}
	// Remove an order from Priority Queue i.e. Deliver an order
	void Deque() {
		int total=0;
		//if priority queue is empty
		if(front==null && rear==null)
		{
			System.out.println("No Order!!");
		}
		else
		{
			// display the details of order delivered
			System.out.println("--------------------------------------");
			System.out.println("Order Delivered: ");
			System.out.println("Customer Name:  "+front.name);
			System.out.println("Customer contact no:  "+front.contactNo);
			System.out.println("-----------Order Details----------");
			double total_bill = 0;
			for(int i=0;i<front.orders.size();i++){
				Node curr = (Node) front.orders.get(i);
				System.out.println(curr.dishName + " : Rs."+ curr.price);
				total_bill += curr.price;
			}
			double tax=0;
			if(total_bill<=100){
				tax=total_bill*0.01;
				total_bill=total_bill+tax;
			}else if (total_bill>100 && total_bill<=500){
				tax=total_bill*0.02;
				total_bill=total_bill+tax;
			}
			else {
				tax=total_bill*0.04;
				total_bill=total_bill+tax;
			}
			System.out.println("Total bill with tax : Rs."+total_bill);
			System.out.println("-------------------------------------");

			if(front==rear) {
				rear=null;
			}
			front=front.next;      // dequeued
		}
	}

	// if Queue is Empty return true
	boolean isEmpty() {
		if(front==null && rear==null) {
			return true;
		}
		else {
			return false;
		}
	}
}

//Class User
class User {
	String name;
	String cnum;
	User(String n,String cn){
		// Constructor to initialize user details
		this.name = n;
		this.cnum=cn;
	}
	// placeorder in specific hotel
	public LinkedList placeorder(Hotel ht){
		LinkedList<Node> al = new LinkedList<Node>();
		String search;
		int total_time=0;
		Node n;
		System.out.println("Enter name of dish your want to order. Press 0 when your done.");
		Scanner sc = new Scanner(System.in);
		while(true){
			String item= sc.nextLine();
			if(item.equals("0"))
				break;
			else{
				// if root is maggie search for the dish in cafe
				if(ht.root.dishName.equalsIgnoreCase("maggie")) {
					n = ht.Search(item,ht.root);
					if(n==null){
						System.out.println("Sorry for inconvenience! Dish Not Available! Please enter again.");
					}
					else {
						//System.out.println(n.dishName);
						total_time += n.time;
						al.add(n);
					}
				}
				// if root is mushroom paneer search for the dish in restro
				if(ht.root.dishName.equalsIgnoreCase("mushroom paneer")) {
					n = ht.Search(item,ht.root);
					if(n==null){
						System.out.println("Sorry for inconvenience! Dish Not Available! Please enter again.");
					}
					else {
						//System.out.println(n.dishName);
						total_time += n.time;
						al.add(n);
					}
				}
				// if root is kunafa search in dessert
				if(ht.root.dishName.equalsIgnoreCase("kunafa")) {
					n = ht.Search(item, ht.root);
					if (n == null) {
						System.out.println("Sorry for inconvenience! Dish Not Available! Please enter again.");
					} else {
						//System.out.println(n.dishName);
						total_time += n.time;
						al.add(n);
					}
				}
			}
		}
		return al;
	}
	// calculate tax in total bill, parameter passed is the total bill, returns the calculated tax as per slab
	public double calTax(double total_p){
		double tax=0;
		if(total_p<=100){
			tax=total_p*0.01;

		}else if (total_p>100 && total_p<=500){
			tax=total_p*0.02;
		}
		else {
			tax=total_p*0.04;
		}
		return tax;
	}
	// display bill to the customer
	public void showBill(String n,String cn, LinkedList bi) {
		double total_p=0;
		double tax=0;
		System.out.println("-----------------------------------");
		System.out.println("Name : "+n);
		System.out.println("Contact no. : "+cn);
		System.out.println();
		for(int i=0; i<bi.size(); i++) {
			Node curr_dish=(Node) bi.get(i);
			System.out.println(curr_dish.dishName +" : Rs."+curr_dish.price );
			total_p+=curr_dish.price;
		}
		double total_pr=total_p;
		tax = calTax(total_p);
		total_p = total_p+tax;

		System.out.println("BILL : Rs."+total_pr);
		System.out.println("Tax Applied : Rs."+tax);
		System.out.println();
		System.out.println("TOTAL BILL WITH TAX : Rs."+total_p);
		System.out.println("-----------------------------------");
		System.out.println("\nThank you for dining with 'IRECA'\n");

	}
}

//Class Admin
class Admin {
	// q is the Queue of customers in that specific Hotel
	PQueue q = new PQueue();
	// Linked list of hotel database
	LL order_Details=new LL();

	// edit menu
	public void editMenu(Node node){

		Node temp=node;
		int updated_T;
		float updated_P;
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);

		System.out.println("Enter the name of dish whose details you want to update : ");
		String DishName = sc1.nextLine();
		int f=0;
		while(temp!=null) {
			if(temp.dishName.equalsIgnoreCase(DishName)) {
				f=1;
				break;
			}
			if(temp.dishName.compareToIgnoreCase(DishName)>0) {
				temp=temp.left;
			}
			if(temp.dishName.compareToIgnoreCase(DishName)<0) {
				temp=temp.right;
			}
		}
		if(f==1) {
			System.out.println("What do you want to update? 1.Time 2.Price 3.Both");
			System.out.println("Enter the choice: ");
			int ch=sc2.nextInt();
			switch(ch) {
				case 1:
					System.out.println("Enter the Updated Time : ");
					updated_T=sc2.nextInt();
					temp.time=updated_T;
					System.out.println("Time updated successfully");
					break;
				case 2:
					System.out.println("Enter the Updated Price : ");
					updated_P=sc2.nextInt();
					temp.price=updated_P;
					System.out.println("Price updated successfully");
					break;
				case 3:
					System.out.println("Enter the Updated Time : ");
					updated_T=sc2.nextInt();
					temp.time=updated_T;
					System.out.println("Enter the Updated Price : ");
					updated_P=sc2.nextInt();
					temp.price=updated_P;
					System.out.println("Time and Price updated successfully");
					break;
				default:
					System.out.println("Invalid Input");
			}

		}
		else {
			System.out.println("Dish Not Found!!!");
		}
	}
	// delete a dish from hotel
	public void Delete(String del_dish,Hotel ht) {

		Node ptr=ht.root,parent=ht.root;
		int flag=0;
		while(ptr!=null)                    //traversing BST
		{
			if (ptr.dishName.compareToIgnoreCase(del_dish)==0)    // word found
			{
				System.out.println("Dish Deleted Successfully!!");
				flag=1;
				break;
			}
			if(ptr.dishName.compareToIgnoreCase(del_dish)<0)                     // if  alphabetically dish is later
			{
				parent=ptr;
				ptr=ptr.right;

			}
			if(ptr.dishName.compareToIgnoreCase(del_dish)>0)                      // if  alphabetically dish is before
			{
				parent=ptr;
				ptr=ptr.left;
			}
		}
		if(flag==1)                                           //if dish found
		{
			while(ptr!=null)
			{
				if(ptr.left==null && ptr.right==null)                //if node is leaf
				{
					if(parent.left==ptr) {
						parent.left=null;
						break;
					}else {
						parent.right=null;
						break;
					}
				}
				else if(ptr.left!=null && ptr.right==null)                 // if node has right child
				{
					if(parent.left==ptr) {
						parent.left=ptr.left;
						break;
					}else {
						parent.right=ptr.left;
						break;
					}
				}
				else if(ptr.left==null && ptr.right!=null)                      // if node has left child
				{
					if(parent.left==ptr) {
						parent.left=ptr.right;
						break;
					}else {
						parent.right=ptr.right;
						break;
					}
				}
				else if(ptr.left!=null && ptr.right!=null)                  // if node has both left and right node
				{
					int flag1=0;
					Node p=ptr.left;                                  // assign node present at left of the node which we want to delete to new node
					parent=ptr;                                // ptr to parent node
					while(p.right!=null)                 //traverse right subtree of left node of node to be deleted
					{
						flag1=1;
						parent=p;
						p=p.right;
					}
					ptr.dishName=p.dishName;
					ptr.time=p.time;
					ptr.price=p.price;
					if(flag1==1){
						parent.right=p.left;
						break;
					}else {
						ptr.left=p.left;
					}
				}
			}
		}else                           // if dish not found
		{
			System.out.println("Dish Not Found!!!");
		}
	}
	// calculate the total income of the specific hotel
	public void totalIncome(LL allData) {
		LLNode temp = allData.head;
		double income=0;
		if(temp==null) {
			System.out.print("Your total income is: ");
			System.out.println("Rs. 0");
			return;
		// no income
		}
		else {
			while(temp!=null) {
				income+=temp.totalBill;
				temp=temp.next;
			}
			System.out.println("Your Total Income : Rs."+income);
		}

	}
}

//class hotel
class Hotel {

	Node root;
	Hotel(){
	// contructor
		root=null;
	}
	// insert new dishes in the hotel, add a node to bst
	void insert(String d,int t,double p) {
		Node temp = new Node(d,t,p);
		if (root == null) {
			root = temp;
		}
		else {
			Node ptr = root;
			boolean flag=false;
			while (flag==false) {
				if (temp.dishName.compareToIgnoreCase(ptr.dishName) < 0) {
					if (ptr.left == null) {
						ptr.left = temp;
						flag=true;
						break;
					}
					else {
						ptr = ptr.left;
					}
				}
				else if (temp.dishName.compareTo(ptr.dishName) > 0) {
					if (ptr.right == null) {
						ptr.right = temp;
						flag=true;
						break;
					}
					else {
						ptr = ptr.right;
					}
				}
			}
		}
	}
	// search and return that node in bst
	public Node Search(String wordToSearch, Node n){
		Node ptr = n;
		while(ptr!=null){
			if(ptr.dishName.equalsIgnoreCase(wordToSearch)){
				return ptr;
			}
			else if(ptr.dishName.compareToIgnoreCase(wordToSearch)>0){
				ptr = ptr.left;
			}
			else if(ptr.dishName.compareToIgnoreCase(wordToSearch)<0){
				ptr = ptr.right;
			}
		}
		return null;
	}
	// Recursive inorder traversal of bst
	public void inOrder(Node node){
		if(node==null){
			return;
		}
		inOrder(node.left);
		System.out.print(node.dishName);
		System.out.print(" : Rs."+node.price+"\n");
		inOrder(node.right);
	}
}

//class cafe
class Cafe extends Hotel{
	// secret code to get the admin access of cafe
	String secretoCode = "cafe123";
	Admin adminCafe = new Admin();
	// add menu to cafe
	public void addMenu(){
		insert("Maggie",12,30);
		insert("Dosa",8,35);
		insert("Sandwich",15,30);
		insert("Coffee",10,50);
		insert("Hamburger",13,55);
		insert("Omlette",9,25);
		insert("Tea",7,20);
		insert("Burger",14,60);
		insert("Pasta",17,40);
	}
	// display menu of the hotel to the customer
	public void displayMenu(Cafe c){
		c.inOrder(c.root);
	}
}

//Class restro
class Restro extends Hotel{
	// secret code to get the admin access of restro
	String secretoCode = "restro123";
	Admin adminRestro  = new Admin();
	// add menu to the restro
	public void addMenu(){
		insert("Mushroom Paneer",10,280);
		insert("Gobi Machurian",15,110);
		insert("Tandoor Chicken",18,350);
		insert("Dal Tadka",12,250);
		insert("Kadhai Paneer",9,55);
		insert("Roti",5,25);
		insert("Veg Cutlet",7,60);
		insert("Naan",8,60);
		insert("Jeera Rice",13,120);
		insert("Kofta Curry",11,250);
		insert("Salad",4,50);
	}
	// display restro menu to the customer
	public void displayMenu(Restro r){
		r.inOrder(r.root);
	}
}
//Class Dessert
class Dessert extends Hotel{
	// secret code to get the admin access of dessert
	String secretoCode = "dessert123";
	Admin adminDess = new Admin();
	// add menu to the dessert
	public void addMenu(){
		insert("Kunafa",18,300);
		insert("Gulab Jamun",7,60);
		insert("Sizzler Brownie",12,190);
		insert("Faluda",10,100);
		insert("Ice-Cream",3,60);
		insert("Fruit Custard",9,40);
		insert("Rasgulla",5,35);
		insert("Waffle",9,120);
		insert("Shahi Jalebi",8,60);
	}
	// display dessert menu to the customer
	public void displayMenu(Dessert d){
		d.inOrder(d.root);
	}
}

//Main class
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Scanner scStr = new Scanner(System.in);
		// create objects of cafe, restro and dessert
		Cafe cafe = new Cafe();
		cafe.addMenu();
		Restro restro = new Restro();
		restro.addMenu();
		Dessert dessert = new Dessert();
		dessert.addMenu();
		// list of multiple orders of a specific customer
		LinkedList orders= new LinkedList();
		boolean more_cus=true;
		while(more_cus) {
			// print hotel name
			System.out.println("" +
					",--.,------. ,------. ,-----.  ,---.   \n" +
					"|  ||  .--. '|  .---''  .--./ /  O  \\  \n" +
					"|  ||  '--'.'|  `--, |  |    |  .-.  | \n" +
					"|  ||  |\\  \\ |  `---.'  '--'\\|  | |  | \n" +
					"`--'`--' '--'`------' `-----'`--' `--' ");
			// 1st interface
			System.out.println("*********WELCOME TO 'IRECA'*********");
			System.out.println("Please select an option: ");
			System.out.println("1 User\n2 Admin");
			int choice1 = sc.nextInt();
			switch (choice1){
				case 1:
				// User Access
					int more = 1;
					System.out.println("1.Cafe \n2.Restro \n3.Dessert ");
					System.out.println("Enter your choice: ");
					// input from which hotel do you want order
					int ch=sc.nextInt();
					System.out.println("Please enter your Name: ");
					String name = scStr.nextLine();
					boolean validCNo = false;
					String cnum="";
					// check the validity of the contact number entered by the user
					while (!validCNo){
						System.out.println("Please enter your Contact no: ");
						cnum = scStr.nextLine();
						if(cnum.length()==10){
							for(int g=0;g<10;g++){
								if(cnum.charAt(g)=='0' || cnum.charAt(g)=='1' || cnum.charAt(g)=='2' || cnum.charAt(g)=='3' || cnum.charAt(g)=='4' || cnum.charAt(g)=='5' || cnum.charAt(g)=='6' || cnum.charAt(g)=='7' || cnum.charAt(g)=='8' || cnum.charAt(g)=='9'){
									validCNo=true;
									continue;
								}
								else {
									validCNo=false;
									break;
								}
							}
						}
						if(cnum.length()!=10){
							System.out.println("INVALID CONTACT NUMBER. PLEASE RE-ENTER");
						}
					}
					User user = new User(name,cnum);
					switch(ch){
						case 1:
							System.out.println("------------MENU-----------");
							// display menu and place the order
							cafe.displayMenu(cafe);
							orders= user.placeorder(cafe);
							break;
						case 2:
							System.out.println("------------MENU-----------");
							// display menu and place the order
							restro.inOrder(restro.root);
							orders= user.placeorder(restro);
							break;
						case 3:
							System.out.println("------------MENU-----------");
							// display menu and place the order
							dessert.inOrder(dessert.root);
							orders= user.placeorder(dessert);
							break;
					}
					int total_time1=0;
					double total_p=0;
					double tax=0;
					// for loop to calculate total time of order of customer, and total bill
					for(int l=0; l<orders.size(); l++) {
						Node d=(Node) orders.get(l);
						total_time1+=d.time;
						total_p+=d.price;
					}
					// calculate the tax as per slab
					if(total_p<=100){
						tax=total_p*0.01;
						total_p=total_p+tax;
					}else if (total_p>100 && total_p<=500){
						tax=total_p*0.02;
						total_p=total_p+tax;
					}
					else {
						tax=total_p*0.04;
						total_p=total_p+tax;
					}
					// Create a node for priority queue
					NodeP n=new NodeP(user.name,user.cnum,total_time1,orders);
					System.out.println("*-*-*-ORDER Recieved-*-*-*");
					System.out.println("-----------BILL DETAILS-----------");
					// display bill
					user.showBill(user.name, user.cnum, orders);
					LLNode cust;
					//Enqueue Order in particular Hotel and add order in particular hotel data base
					switch (ch){
						case 1:
							cafe.adminCafe.q.Enque(n);
							cust=new LLNode(user.name,total_p,user.cnum,orders);
							cafe.adminCafe.order_Details.insert(cust);
							break;
						case 2:
							restro.adminRestro.q.Enque(n);
							cust=new LLNode(user.name,total_p,user.cnum,orders);
							restro.adminRestro.order_Details.insert(cust);
							break;
						case 3:
							dessert.adminDess.q.Enque(n);
							cust=new LLNode(user.name,total_p,user.cnum,orders);
							dessert.adminDess.order_Details.insert(cust);
							break;
					}
					break;
				case 2:
					//Admin Access

					Scanner sc2 = new Scanner(System.in); //for string inputs
					Scanner sc3 = new Scanner(System.in);
					Scanner sc4 = new Scanner(System.in);
					// accept the name of hotel
					System.out.println("Enter the name of your Hotel: ");
					String branch = sc2.next().toLowerCase();
					String code;
					int flag=0;
					switch (branch)
					{

						case "cafe":
							System.out.println("Please Enter the security code of "+branch+": ");
							//Accept password for admin access
							code = sc2.next().toLowerCase();
							if (code .equals( cafe.secretoCode))
							{
								flag=1;
								System.out.println("Admin Access for Cafe Granted: ");
								int opt=1;
								while(opt!=0) {
									System.out.println("--OPTIONS--");
									System.out.println("0.Log out");
									System.out.println("1. Update the menu");
									System.out.println("2. Add New Menu");
									System.out.println("3. Deliver and view delivered order details");
									System.out.println("4. View all remaining orders to be delivered");
									System.out.println("5. View all Orders Received");
									System.out.println("6. View Total Income");
									System.out.println("7. Delete a dish from cafe menu");
									System.out.println("Enter The Option : ");
									opt=sc3.nextInt();
									switch(opt) {
										case 0:
										//Logged out from Admin mode
											System.out.println("Logged Out!!");
											break;
										case 1:
											// edit the existing hotel menu
											System.out.println("----------Menu----------");
											cafe.displayMenu(cafe);
											cafe.adminCafe.editMenu(cafe.root);
											break;
										case 2:
										// add new menu
											System.out.println("Enter the name of the new Dish: ");
											String newDish=sc4.nextLine();
											System.out.println("Enter the price of the Dish: ");
											double newP=sc3.nextDouble();
											System.out.println("Enter the time for the preparation of Dish: ");
											int newT=sc3.nextInt();
											cafe.insert(newDish, newT, newP);
											System.out.println(newDish+" added successfully to Menu");
											break;
										case 3:
											//Display the delivered order details
											boolean areOrders = cafe.adminCafe.q.isEmpty();
											cafe.adminCafe.q.Deque();
											if(!areOrders) {
												System.out.println("Rate Us: 1 2 3 4 5\nEnter the number!");
												int rating = sc.nextInt();
												System.out.println("Thank you for rating!");
												switch (rating) {
													case 1:
														System.out.println("⭐");
														break;
													case 2:
														System.out.println("⭐⭐");
														break;
													case 3:
														System.out.println("⭐⭐⭐");
														break;
													case 4:
														System.out.println("⭐⭐⭐⭐");
														break;
													case 5:
														System.out.println("⭐⭐⭐⭐⭐");
													default:
														break;
												}
												System.out.println("Please enter your feedback: ");
												String feedback = scStr.nextLine();
												System.out.println("Feedback received! Thank you!");
											}

											break;

										case 4:
											//display all remaining orders to be delivered
											cafe.adminCafe.q.Display();
											break;
										case 5:
										//display all received orders
											cafe.adminCafe.order_Details.displayDataOfAll();
											break;
										case 6:
											// calculate total income
											cafe.adminCafe.totalIncome(cafe.adminCafe.order_Details);
											break;
										case 7:
											System.out.print("Enter the dish name to be deleted from menu: ");
											String del_name=scStr.nextLine();
											cafe.adminCafe.Delete(del_name,cafe);
											break;
										default:
											System.out.println("Invalid Option!!!");
									}
								}
							}
							if(flag==0) {
								System.out.println("Incorrect Security pin!!");

							}
							break;
						case "restro":
							System.out.println("Please Enter the security code of "+branch+": ");
							code = sc2.next().toLowerCase();
							if (code.equals( restro.secretoCode))
							{
								flag=1;
								int opt=1;
								System.out.println("Admin Access for Restro Granted: ");
								while(opt!=0) {

									System.out.println("--OPTIONS--");
									System.out.println("0. Logout");
									System.out.println("1. Update the menu");
									System.out.println("2. Add New Menu");
									System.out.println("3. Deliver and view delivered order details");
									System.out.println("4. View all remaining orders to be delivered");
									System.out.println("5. View all Orders Received");
									System.out.println("6. View Total Income");
									System.out.println("7. Delete a dish from cafe menu");
									System.out.println("Enter The Option : ");
									opt=sc3.nextInt();
									switch(opt) {
										case 0:
										//Logged out from Admin mode
											System.out.println("Logged Out!!");
											break;
										case 1:
										// edit the existing hotel menu
											System.out.println("----------Menu----------");
											restro.displayMenu(restro);
											restro.adminRestro.editMenu(restro.root);
											break;
										case 2:
										// add new menu
											System.out.println("Enter the name of the new Dish: ");
											String newDish=sc4.nextLine();
											System.out.println("Enter the price of the Dish: ");
											double newP=sc3.nextDouble();
											System.out.println("Enter the time for the preparation of Dish: ");
											int newT=sc3.nextInt();
											restro.insert(newDish, newT, newP);
											System.out.println(newDish+" added successfully to Menu");
											break;
										case 3:
										//Display the delivered order details
											boolean areOrders = restro.adminRestro.q.isEmpty();
											restro.adminRestro.q.Deque();
											if (!areOrders) {
												System.out.println("Rate Us: 1 2 3 4 5\nEnter the number!");
												int rating = sc.nextInt();
												System.out.println("Thank you for rating!");
												switch (rating){
													case 1:
														System.out.println("⭐");
														break;
													case 2:
														System.out.println("⭐⭐");
														break;
													case 3:
														System.out.println("⭐⭐⭐");
														break;
													case 4:
														System.out.println("⭐⭐⭐⭐");
														break;
													case 5:
														System.out.println("⭐⭐⭐⭐⭐");
													default:
														break;
												}
												System.out.println("Please enter your feedback: ");
												String feedback = scStr.nextLine();
												System.out.println("Feedback received! Thank you!");
											}

											break;
										case 4:
											//display all remaining orders to be delivered
											restro.adminRestro.q.Display();
											break;
										case 5:
										//display all received orders
											restro.adminRestro.order_Details.displayDataOfAll();
											break;
										case 6:
										// calculate total income
											restro.adminRestro.totalIncome(restro.adminRestro.order_Details);
											break;
										case 7:
											// delete from the menu
											System.out.print("Enter the dish name to be deleted from menu: ");
											String del_name=scStr.nextLine();
											cafe.adminCafe.Delete(del_name,restro);
											break;
										default:
											System.out.println("Invalid Option!!!");
									}
								}
							}
							if(flag==0) {
								System.out.println("Incorrect Security pin!!");

							}
							break;
						case "dessert":
							System.out.println("Please Enter the security code of "+branch+": ");
							code = sc2.next().toLowerCase();
							if (code .equals( dessert.secretoCode))
							{
								flag=1;
								int opt=1;
								System.out.println("Admin Access for Dessert Granted: ");
								while(opt!=0) {

									System.out.println("--OPTIONS--");
									System.out.println("0. Logout");
									System.out.println("1. Update the menu");
									System.out.println("2. Add New Menu");
									System.out.println("3. Deliver and view delivered order details");
									System.out.println("4. View all remaining orders to be delivered");
									System.out.println("5. View all Orders Received");
									System.out.println("6. View Total Income");
									System.out.println("7. Delete a dish from cafe menu");
									System.out.println("Enter The Option : ");
									opt=sc3.nextInt();
									switch(opt) {
										case 0:
										//Logged out from Admin mode
											System.out.println("Logged Out!!");
											break;
										case 1:
										// edit the existing hotel menu
											System.out.println("----------Menu----------");
											dessert.displayMenu(dessert);
											dessert.adminDess.editMenu(dessert.root);
											break;
										case 2:
										// add new menu
											System.out.println("Enter the name of the new Dish: ");
											String newDish=sc4.nextLine();
											System.out.println("Enter the price of the Dish: ");
											double newP=sc3.nextDouble();
											System.out.println("Enter the time for the preparation of Dish: ");
											int newT=sc3.nextInt();
											dessert.insert(newDish, newT, newP);
											System.out.println(newDish+" added successfully to Menu");
											break;
										case 3:
										//Display the delivered order details
											boolean areOrders = dessert.adminDess.q.isEmpty();
											dessert.adminDess.q.Deque();
											if(!areOrders) {
												System.out.println("Rate Us: 1 2 3 4 5\nEnter the number!");
												int rating = sc.nextInt();
												System.out.println("Thank you for rating!");
												switch (rating) {
													case 1:
														System.out.println("⭐");
														break;
													case 2:
														System.out.println("⭐⭐");
														break;
													case 3:
														System.out.println("⭐⭐⭐");
														break;
													case 4:
														System.out.println("⭐⭐⭐⭐");
														break;
													case 5:
														System.out.println("⭐⭐⭐⭐⭐");
													default:
														break;
												}
												System.out.println("Please enter your feedback: ");
												String feedback = scStr.nextLine();
												System.out.println("Feedback received! Thank you!");
											}

											break;

										case 4:
											// display all remaining orders to be delivered
											dessert.adminDess.q.Display();
											break;
										case 5:
										//display all received orders
											dessert.adminDess.order_Details.displayDataOfAll();
											break;
										case 6:
										// calculate total income
											dessert.adminDess.totalIncome(dessert.adminDess.order_Details);
											break;
										// delete a dish from menu of dessert
										case 7:
											System.out.print("Enter the dish name to be deleted from menu: ");
											String del_name=scStr.nextLine();
											cafe.adminCafe.Delete(del_name,dessert);
											break;
										default:
											System.out.println("Invalid Option!!!");
									}
								}
							}
							if(flag==0) {
								System.out.println("Incorrect Security pin!!");

							}
							break;

						default:
							System.out.println("BRANCH NOT FOUND!");
					}
					break;    //break case 2 ie access to admin

				default:
					System.out.println("INVALID INPUT!! ");
			}
			System.out.println("Do you want to continue? Enter 1 for 'yes' and 0 for 'no'");
			int cus=sc.nextInt();
			if(cus==1) {
				more_cus=true;
			}
			else if(cus==0){
				System.out.println("Thank you for connecting with 'IRECA'. Hope to see you again soon!");
				System.exit(0);
			}
			else {
				System.out.println("Invalid Input! ");
				more_cus=true;
			}
		}
	}
}
