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
  display1 : Full_Display = <Full_Display>{};
  id!: number;
  user!: string;
  profileId!: number;

  //initial profile
  visitprofile : Profiled = <Profiled>{};
  displays : Array<Full_Display> = [];

  constructor(private apiServ : ApiService, private route : ActivatedRoute) {
   }



  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
      this.user = params['user']  
    })
   
    this.getOneProfileByUsername();    
  }
 
  getOneProfileByUsername(){
    this.apiServ.getOneProfileByUsername1().subscribe(response => {     
      this.visitprofile = response.data; 
      this.profileId = response.data.profileId;  
      
      this.apiServ.visitUser = this.visitprofile;
      this.apiServ.profileid = this.profileId; 
      
      this.getAllDisplaysGivenProfileId()
    })

  }
  getAllDisplaysGivenProfileId(){
    this.apiServ.getAllGivenProfileId(this.profileId).subscribe(response => {
      this.displays = response.data;
     // console.log(response.data)
    })
  }

  changeImage(e : any){
    if(e.innerText = `[ngStyle]="{'background-image' : 'url( '+${this.bg3} +')' }"`)
    this.bg3 = this.bg2; 
  }

  }
  

