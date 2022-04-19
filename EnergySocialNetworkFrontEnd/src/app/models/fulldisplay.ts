import { Profile } from "./profile";

export interface Full_Display{
    displayId: number;
    image: {
        image: string;
    }
    profile: Profile;
    likers: {
        profile:Profile;
    }
    description: string;
}