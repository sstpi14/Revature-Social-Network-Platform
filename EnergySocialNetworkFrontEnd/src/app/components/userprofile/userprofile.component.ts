import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Profile } from 'src/app/models/profile';
import { DisplayServiceService } from 'src/app/service/display-service.service';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {

  isVisible : boolean = false;
  displays: Array<any> = [];
  profiles: Array<Profile> = [];
  isLiked:boolean = false;
  id!: number;
  user!: string;
  profileId!: number;
  JBG3: string = "http://4.bp.blogspot.com/-QlePmWOihy0/VWwXhIBhfyI/AAAAAAAAdS4/2h8cu3v2iN4/s1600/201b0ace7a603787e553701e6c679791d579d9e8.jpeg";
  file!:any;
  isEditPicture: boolean = false;

 

  profile : any = {
    profileId: 0,
    username: '',
    firstname: '',
    lastname: '',
    image: '',
  }

  constructor(private dispaySer : DisplayServiceService, private apiServ : ApiService, private router : Router, private route : ActivatedRoute) {
   }



  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
      this.user = params['user']
    })
    
    
    this.getAllDisplays();
    this.getOneProfileByProfileId(); 
  }
  
  getAllDisplaysGivenProfileId(){
    this.apiServ.getAllGivenProfileId(this.profileId).subscribe(response => {
      this.displays = response.data;
    })
  }

  getAllDisplays(): void {
    this.dispaySer.getAllDisplays().subscribe(responseBody =>{
      this.displays = responseBody;
      //this.profile = responseBody[0].profiles;
      console.log(this.displays);
    })
  }

  getOneProfileByProfileId(){
    this.apiServ.getOneProfileByUsername(this.user).subscribe(response => {
      this.profile = response.data;
      this.profileId = response.data.profileId;
      console.log(this.profile);
      console.log(this.profile.profileId);
      console.log(this.id);
      if(this.id == this.profileId) {
        this.isVisible = true;
      }
    })
  }
  goToEdit(){
    this.router.navigate(["/edit"], { queryParams: { id: this.profile.profileId }});
    console.log(this.profile.profileId);
  }
  
  goToUser(e:any){
    this.router.navigate(["/user"], { queryParams: { user: e.target.innerText, id: this.profile.profileId }});
    console.log(e.target.innerText);
  }
  goToUserHome(){
    this.router.navigate(["/home"], { queryParams: { id: this.id }});
    //console.log(this.id);
  }
  getFile(event:any){
    this.file = event.target.files[0];
  }
  submitProfilePicture(){
    let formData = new FormData();
    formData.append('file', this.file);
    this.apiServ.uploadProfilePic(formData, this.id).subscribe(responseBody => {
      this.getOneProfileByProfileId();
    })
  }
  isDivVisible(){
    if (this.isEditPicture == true) {
      this.isEditPicture = false;
    } else {
      this.isEditPicture = true;
    }
  }

}
