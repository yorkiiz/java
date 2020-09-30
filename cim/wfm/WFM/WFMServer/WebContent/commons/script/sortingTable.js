
// positionArray is used to generate new order row
var positionArray ;
var currentPositionArray = new Array();

var orderDesc;



// recontruct data for sorting..
function setDataType(cValue)
{
  // THIS FUNCTION CONVERTS DATES AND NUMBERS FOR PROPER ARRAY
  // SORTING WHEN IN THE SORT FUNCTION
  var isDate = new Date(cValue);
  if(isDate == "NaN"){
    if (isNaN(cValue)){
      // THE VALUE IS A STRING, MAKE ALL CHARACTERS IN
      // STRING UPPER CASE TO ASSURE PROPER A-Z SORT
      //cValue = cValue.toUpperCase();
      return cValue;
    }
    else{
      // VALUE IS A NUMBER, TO PREVENT STRING SORTING OF A NUMBER
      // ADD AN ADDITIONAL DIGIT THAT IS THE + TO THE LENGTH OF
      // THE NUMBER WHEN IT IS A STRING
      var myNum;
      myNum = String.fromCharCode(48 + cValue.length) + cValue;
      return myNum;
    }
  }
  else{
    // VALUE TO SORT IS A DATE, REMOVE ALL OF THE PUNCTUATION AND
    // AND RETURN THE STRING NUMBER
    // BUG - STRING AND NOT NUMERICAL SORT .....
    // ( 1 - 10 - 11 - 2 - 3 - 4 - 41 - 5  etc.)
    var myDate = new String();
    myDate = isDate.getFullYear() + " " ;
    myDate = myDate + isDate.getMonth() + " ";
    myDate = myDate + isDate.getDate(); + " ";
    myDate = myDate + isDate.getHours(); + " ";
    myDate = myDate + isDate.getMinutes(); + " ";
    myDate = myDate + isDate.getSeconds();
    //myDate = String.fromCharCode(48 + myDate.length) + myDate;
   // alert(myDate);
    return myDate ;
  }
}



// initial the original position 
function initialpositionArray(arraylength){
	
	if ( (currentPositionArray ==null)||(currentPositionArray.length<1 )){
		currentPositionArray = new Array(arraylength);
		
		for ( i=0;i<arraylength;i++)
			currentPositionArray[i] = i;
	}	
	
	positionArray= new Array(arraylength);
	for ( i=0;i<arraylength;i++){
		positionArray[i]=i;	
	}		
}	


function initialOrderDesc(totalcols){
	

	orderDesc = new Array(totalcols);
	for (i=0;i<totalcols;i++){	
		orderDesc[i]=0;	
	}
}	


// quicksortIncr : (increase)
// param 1 sortingArray : source array that is needed sort
// param 2 low			: low position
// param 3 high			: high position
function quicksortIncr (sortingArray, low, high){

	var i = low;
	var j = high;
	var h;
	var z;
	var r = (low+high) % 2 ;
	var x = sortingArray[(low+high-r)/2];
	
    do{
        while (sortingArray[i]<x) i++;
        while (sortingArray[j]>x) j--;

       	if (i<=j){
          	h=sortingArray[i]; sortingArray[i]=sortingArray[j]; sortingArray[j]=h;
          	z=positionArray[i];positionArray[i]=positionArray[j];positionArray[j]=z;
          	z=currentPositionArray[i];currentPositionArray[i]=currentPositionArray[j];currentPositionArray[j]=z;
          	i++; j--;
       	}
    } while (i<=j);

    if (low<j) quicksortIncr(sortingArray, low, j);
    if (i<high) quicksortIncr(sortingArray, i, high);
}


// quicksortDesc : (desc)
// param 1 sortingArray : source array that is needed sort
// param 2 low			: low position
// param 3 high			: high position
function quicksortDesc (sortingArray, low, high){

	var i = low;
	var j = high;
	var h;
	var z;
	var r = (low+high) % 2 ;
	var x = sortingArray[(low+high-r)/2];
	
    do{
        while (sortingArray[i]>x) i++;
        while (sortingArray[j]<x) j--;

       	if (i<=j){
          	h=sortingArray[i]; sortingArray[i]=sortingArray[j]; sortingArray[j]=h;
          	z=positionArray[i];positionArray[i]=positionArray[j];positionArray[j]=z;
          	z=currentPositionArray[i];currentPositionArray[i]=currentPositionArray[j];currentPositionArray[j]=z;
          	i++; j--;
       	}
    } while (i<=j);

    if (low<j) quicksortDesc(sortingArray, low, j);
    if (i<high) quicksortDesc(sortingArray, i, high);
}


// when you append existed child, the order will remove its original position.
// So, we must prepare new order
function reorderforAppendTable(){

	count = positionArray.length;
	for (i=0;i<count;i++){
		for ( j=i+1;j<count;j++){
			if ( positionArray[j]>positionArray[i] )
				positionArray[j]--;	
		}
	}
}


//sorting table
function sortTable(col, tablename, flag){

	var startDate = new Date();
	var tableNode = document.getElementById(tablename);
	var tableCollection = tableNode.childNodes;
	var tableChildCount = tableCollection.length;
	var tbodyNode ;

	
	// default assumption : table structure is  <THEAD></THEAD> <TBODY> </TBODY>
	if ( tableChildCount >1 )
		tbodyNode = tableCollection[1];
	else
		tbodyNode = tableCollection[0];	
	
	// total counts of records 
	var tbodyRowsCount = tbodyNode.childNodes.length;
	var tbodyRowsCollection = tbodyNode.childNodes;
	
	// initialize the order flag ( increase or descrease)
	if ( orderDesc==null) 
		initialOrderDesc(tbodyRowsCount);
	
	// true is desc , false is asc
	if(flag){
		//alert("aa:"+col+":"+orderDesc[col]);
		orderDesc[col]=1;
  	}
	
	// get the values to compare
	var colArray = new Array(tbodyRowsCount);
	for ( i=0;i<tbodyRowsCount ;i++){
		colArray[i]= setDataType(tbodyRowsCollection[i].childNodes[col].innerText);
	}	
	
	
	initialpositionArray(tbodyRowsCount);

	// decide the order is incr or desc	
	if ( orderDesc[col]>0){
		orderDesc[col]=0;
		quicksortDesc(colArray,0,colArray.length-1);
		
	}else{	
		quicksortIncr(colArray, 0, colArray.length-1);
		orderDesc[col]=1;
	}

	// re-generate the orders because the method of appendChild will remove old items
	reorderforAppendTable();
	
	for ( i=0;i<tbodyRowsCount;i++){
		if (i%2==0)
			tbodyRowsCollection[positionArray[i]].className = "tdbg_white";
		else
			tbodyRowsCollection[positionArray[i]].className = "td-lightcolor";
		tbodyNode.appendChild(tbodyRowsCollection[positionArray[i]]);

		//alert(i + ":" + positionArray[i] + ":" + orderDesc[col]);
	}	
		
	var endDate = new Date();	

}
