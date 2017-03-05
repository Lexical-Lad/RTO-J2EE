function validate()
{	
    document.getElementById("dob").innerHTML="";
    document.getElementById("em").innerHTML= "";
    document.getElementById("st").innerHTML="";
    document.getElementById("p").innerHTML="";
    document.getElementById("cp").innerHTML= "";
    
    var bool=true;
    var d=document.reg.dd.value;
    var m=document.reg.mm.value;
    var y=document.reg.yyyy.value;
    
    var birth=new Date(parseInt(y),(parseInt(m)-1),parseInt(d));
    
    var sixteen=504911232000;
	
    var date=new Date();

    if(!(/^\d+$/.test(d))||!(/^\d+$/.test(m))||!(/^\d+$/.test(y))||d.length>2||m.length>2||parseInt(d)>31||parseInt(m)>12||y.length<4)
    {
        document.getElementById("dob").innerHTML="**  Invalid Date";
        bool=false;
    }
    
    else if(date.getTime()<birth.getTime())
        {
            document.getElementById("dob").innerHTML="**  You ain't born yet?";
            bool=false;
        
        }
    
        else if((date.getTime()-birth.getTime())<sixteen)
                {
                    document.getElementById("dob").innerHTML="**  Take a seat, Kid. You ain't 16 yet!";
                    
                    bool=false;
                }
        
    
    var e=document.reg.email.value;
    
    var atposition=e.indexOf("@");
    var dotposition=e.lastIndexOf(".");
    
    if((atposition===-1)||(dotposition===-1)||(atposition!==e.lastIndexOf("@"))||(atposition<1)||(dotposition<(atposition+2))||((dotposition+2)>=e.length))
    {
        document.getElementById("em").innerHTML= "**  Invalid Email";
        bool=false;
    }
    
    var st=document.reg.state.value;
    if(st==="select")
    {
        document.getElementById("st").innerHTML="**   Please Select a State!";
        
        bool=false;
    }
    
    p=document.reg.pass.value;
    p1=document.reg.pass1.value;
    
    if(p.length<8)
    {
        document.getElementById("p").innerHTML=" ** Password must be at least 8 characters long!";
        bool=false;
    }
    
    else if(!(p===p1))
        {
            document.getElementById("cp").innerHTML= "  ** The passwords do not match!";
            bool=false;
        }
    
    if(bool===false)
	return false;
    
}