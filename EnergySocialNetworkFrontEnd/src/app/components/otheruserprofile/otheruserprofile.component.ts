import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Profiled } from 'src/app/models/dprofile';
import { Full_Display } from 'src/app/models/fulldisplay';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-otheruserprofile',
  templateUrl: './otheruserprofile.component.html',
  styleUrls: ['./otheruserprofile.component.css']
})
export class OtheruserprofileComponent implements OnInit {

  bg1 :string =     "https://www.wbcsd.org/var/site/storage/images/media/page-assets/new-projects/new-energy-solutions/new-energy-solutions/63873-4-eng-GB/New-energy-solutions_i1140.jpg";
  bg2 :string =     "https://cosmosmagazine.com/wp-content/uploads/2021/04/What-is-energy.jpg" ;
  bg3 :string =     "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRba_vlE_ij-BuCj3-jD4_gsAJy52h0F3IeIw&usqp=CAU" ;
  bg4 :string = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfk6h3AH-QTyShrUxmhjl8LzuZvY4otcjvbg&usqp=CAU" ;
  glit : string = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTES9iSF7zIJUxwufm2LXaGTz-BnbEZyQX78g&usqp=CAU"; 
  shine : string = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNUl7AVFOJxD-C5Rc2hxmMAiNrEdloYwk10Q&usqp=CAU";
  
  isVisible : boolean = false;
  profiles: Array<Profiled> = [];
  displayId : number = 0;
  id!: number;
  user!: string;
  profileId!: number;
  liked : boolean = false;
  user_img!: string;

  //initial profile
  visitprofile : Profiled = <Profiled>{};
  //initial display
  display : Full_Display = <Full_Display>{};
  //initial displays
  displays : Array<Full_Display> = [];
  //initial likes
  likes : Array<Profiled> = [];

  constructor(private apiServ : ApiService, private route : ActivatedRoute, private router : Router) {
   }



  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
      this.user = params['user']  
    })

   
   console.log(this.display.likers);
    this.getOneProfileByUsername();    
  }
 
  getOneProfileByUsername(){
    this.apiServ.getOneProfileByUsername(this.user).subscribe(response => {     
      this.visitprofile = response.data; 
      console.log(response.data.img)
      this.profileId = response.data.profileId;  
      
      this.apiServ.visitUser = this.visitprofile;
      this.apiServ.profileid = this.profileId; 
      this.user_img = response.data.img;
      console.log(this.user_img)
      
      this.getAllDisplaysGivenProfileId();
      
    })

  }
  getAllDisplaysGivenProfileId(){
    this.apiServ.getAllGivenProfileId(this.profileId).subscribe(response => {
      this.displays = response.data;
     // console.log(response.data)
    })
  }

  like(e : any, displayId : number){
    //first get one display
    console.log(e.innerText, displayId);
    this.apiServ.getOneDisplay(displayId).subscribe(response => {
      this.apiServ.display = response.data;
       //change inner text
       this.liked = !this.liked;
    });
   //call service
    this.apiServ.addLikeOrDislike(displayId, this.apiServ.currentUser.profileId, this.apiServ.display).subscribe(response => {
     // console.log(response);
    })

    }

  likers(e: any, displayId : number){
    //console.log(e, displayId);
    this.apiServ.getAllLikersOnDisplay(displayId).subscribe(response => {
      console.log(response);
    })
   
    console.log(length)
     this.likes.filter(function(item){
      console.log(item)
    }).length;
  }
  

  changeImage(e : any){
    if(e.innerText = `[ngStyle]="{'background-image' : 'url( '+${this.bg3} +')' }"`)
    this.bg3 = this.bg2; 
  }

  goToUserHome(){
    this.router.navigate(["/home"], { queryParams: { id: this.id }});
    console.log(this.id);
  }

  }
  

