import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { display } from 'src/app/models/display';
import { Profile } from 'src/app/models/profile';
import { DisplayServiceService } from 'src/app/service/display-service.service';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-userdisplayblock',
  templateUrl: './userdisplayblock.component.html',
  styleUrls: ['./userdisplayblock.component.css']
})
export class UserdisplayblockComponent implements OnInit {

  post_desciption:string="";
  displays:Array<any> = [];
  profiles:Array<Profile> = [];
  isLiked:boolean = false;
  isVisable:boolean = false;
  id!: number;
  file!: any;

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
  // dispaySer: any;

  constructor(private dispayServ : DisplayServiceService, private route : ActivatedRoute, private router : Router, private apiServ : ApiService) {}

  ngOnInit(): void {
    this.route.queryParams
    .subscribe((params: { [x: string]: number; }) => {
      this.id = params['id'];
    })
    this.getOneProfile();
    this.getAllDisplays();
  }

  getOneProfile(){
    this.apiServ.getOneProfileByProfileId(this.id).subscribe((response: { data: any; }) => {
      this.profile = response.data;
    })
  }


  getAllDisplays(): void {
    this.dispayServ.getAllDisplaysbyId(this.id).subscribe(responseBody =>{
      console.log(responseBody)
      this.displays = responseBody.data;
    })
  }

  getOneDisplay(): void{
    this.apiServ.getOneDisplay(this.id).subscribe(response =>{
      this.profile = response.data.displayId;
    })
  }


  goToPersonalUser(e:any){
    this.apiServ.getOneProfileByProfileId(this.id).subscribe((response: { data: any; }) => {
      this.profile = response.data;
      this.router.navigate(["/user"], { queryParams: { user: e.target.innerText, id: this.profile.profileId }});
      console.log(this.profile.username)
    })
  }
  goToUser(e:any){
    this.router.navigate(["/user"], { queryParams: { user: e.target.innerText, id: this.profile.profileId }});
    console.log(e.target.innerText);
  }

  goToOtherUser(e:any){
    this.router.navigate(["/otheruser"]);
    console.log(e.target.innerText);
  }

  toggleLike(e:any,displayId:number){
    e.target.like = !e.target.like;
    this.apiServ.addLikeOrDislike(displayId,this.id,this.display).subscribe(response=>{
      this.getAllDisplays();
    });
  }
  isUserInLikesArray(likers : Array<any>){
    for (let liker of likers) {
      if(liker.profileId == this.id) {
        return "Dislike"
      }
    }
    return "Like"
  }


  togglePost(){
    this.isVisable = !this.isVisable;
  }


  post(){
    this.display.description = this.post_desciption;
    this.display.profile.profileId = this.id;
    this.dispayServ.createDisplay(this.display).subscribe(response=>{
      let formData = new FormData();
      formData.append('file', this.file);
      this.apiServ.uploadDisplayImage(formData, this.id, response.data.displayId).subscribe(responseBody => {
        this.getAllDisplays();
      });
      this.post_desciption = "";
      
    })
  }
  getFile(event:any){
    this.file = event.target.files[0];
  }

}
