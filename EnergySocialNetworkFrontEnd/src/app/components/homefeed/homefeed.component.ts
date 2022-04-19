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
  user:display={username:"matt",desciption:"hello",img:"",like:false};
  user2:display={username:"kev",desciption:"bye",img:"",like:true};
  //displays:Array<display> = [ this.user,this.user2 ];
  displays:Array<any> = [];
  profiles:Array<Profile> = [];
  isLiked:boolean = false;
  isVisable:boolean = false;
  id!: number;

  profile : Profiled = {
    profileId: 0,
    username: '',
    firstname: '',
    lastname: '',
    image: ''
  }
  constructor(private dispaySer : DisplayServiceService, private route : ActivatedRoute, private router : Router, private apiServ : ApiService) {}

  ngOnInit(): void {
    //this.getAllDisplays
    this.route.queryParams
    .subscribe(params => {
      this.id = params['id'];
    })
    this.getOne();
  }

  getOne(){
    this.apiServ.getOneProfileByProfileId(this.id).subscribe(response => {
      this.profile = response.data;
      console.log(this.profile);
    })
  }


  getAllDisplays(): void {
    this.dispaySer.getAllDisplays().subscribe(responseBody =>{
      this.displays = responseBody;
      this.profile = responseBody[0].profiles;
      console.log(this.displays);
    })
  }

  goToUser(e:any){
    this.router.navigate(["/user"]);
    console.log(e.target.innerText);
  }

  goToOtherUser(e:any){
    this.router.navigate(["/otheruser"]);
    console.log(e.target.innerText);
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
    console.log(this.post_desciption);
  }

}
