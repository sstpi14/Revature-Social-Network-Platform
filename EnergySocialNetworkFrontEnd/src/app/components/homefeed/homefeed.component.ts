import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { display } from 'src/app/models/display';
import { Profiled } from 'src/app/models/dprofile';
import { Profile } from 'src/app/models/profile';
import { DisplayServiceService } from 'src/app/service/display-service.service';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-homefeed',
  templateUrl: './homefeed.component.html',
  styleUrls: ['./homefeed.component.css']
})
export class HomefeedComponent implements OnInit {

  post_desciption:string=""
  displays:Array<any> = [];
  profiles:Array<Profile> = [];
  isLiked:boolean = false;
  isVisable:boolean = false;
  id!: number;

  profile : any = {
    profileId: 0,
    username: '',
    firstname: '',
    lastname: '',
    image: ''
  }
  display: display = {
    description : "",
    //img : "",
    profile : this.profile
  };
  constructor(private dispaySer : DisplayServiceService, private route : ActivatedRoute, private router : Router, private apiServ : ApiService) {}

  ngOnInit(): void {
    //this.getAllDisplays
    this.route.queryParams
    .subscribe(params => {
      this.id = params['id'];
    })
    this.getOneProfile();
    this.getAllDisplays();
  }

  getOneProfile(){
    this.apiServ.getOneProfileByProfileId(this.id).subscribe(response => {
      this.profile = response.data;
      
      this.apiServ.currentUser = this.profile
      console.log(this.apiServ.currentUser);
    })
  }


  getAllDisplays(): void {
    this.dispaySer.getAllDisplays().subscribe(responseBody =>{
      this.displays = responseBody;
      //this.profile = responseBody[0].profiles;
  
    })
  }

  goToPersonalUser(e:any){
    this.apiServ.getOneProfileByProfileId(this.id).subscribe(response => {
      this.profile = response.data;
      if(e.target.innerText === this.profile.username){
          this.goToUser(e.target.innerText);     }
      else{
          this.goToOtherUser(e.target.innerText);      }
    //  console.log(this.profile.username)
    })
  }



  goToUser(e:any){
    this.router.navigate(["/user"], { queryParams: { user: e.target.innerText, id: this.profile.profileId }});

  }
  
  goToOtherUser(e: any){
    let username : string = e.target.innerText;
    this.apiServ.username = username;
    this.router.navigate(["/otheruser"], { queryParams: { user: e.target.innerText, id: this.profile.profileId }});
  
  }

  toggleLike(e:any,displayId:number){
    e.target.like = !e.target.like;
    this.apiServ.addLikeOrDislike(displayId,this.id,this.display).subscribe(response=>{
      console.log(response);
    });
    if(e.target.like == true){
      e.target.innerText = "Dislike";
    }else{
      e.target.innerText = "Like";
    }
    //this.isLiked=!this.isLiked;
  }

  togglePost(){
    this.isVisable = !this.isVisable;
  }

  /* 
  sends a post request
  brings you back to the homefeed
  */
  post(){
    this.display.description = this.post_desciption;
    this.display.profile.profileId = this.id;
    this.dispaySer.createDisplay(this.display).subscribe(response=>{
      this.post_desciption = "";
      this.displays.push(response.data);
    })
  }

}
