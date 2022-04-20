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
    profile : {
      profileId : this.id
    }
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
      console.log(this.profile);
    })
  }


  getAllDisplays(): void {
    this.dispaySer.getAllDisplays().subscribe(responseBody =>{
      this.displays = responseBody;
      //this.profile = responseBody[0].profiles;
      console.log(this.displays);
    })
  }

  goToPersonalUser(e:any){
    this.apiServ.getOneProfileByProfileId(this.id).subscribe(response => {
      this.profile = response.data;
      if(e.target.innerText === this.profile.profileId){
          this.goToUser(e.target.innerText);     }
      else{
          this.goToOtherUser(e.target.innerText);      }
      console.log(this.profile.username)
    })
  }



  goToUser(e:any){
    this.router.navigate(["/user"], { queryParams: { user: e.target.innerText, id: this.profile.profileId }});
    console.log(e.target.innerText);
  }
  //wasnt being called anywhere
  goToOtherUser(e:any){
    this.router.navigate(["/otheruser"], { queryParams: { user: e.target.innerText, id: this.profile.profileId }});
    console.log();
    console.log(this.getOneProfile());
  }

  toggleLike(e:any){
    e.target.like = !e.target.like;
    if(e.target.like == true){
      e.target.innerText = "Dislike";
    }else{
      e.target.innerText = "Like";
    }
    console.log(e.target.like);
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
    console.log(this.display);
    this.display.profile.profileId = this.id;
    this.dispaySer.createDisplay(this.display).subscribe(response=>{
      this.post_desciption = "";
      this.displays.push(response.data);
    })
  }

}
