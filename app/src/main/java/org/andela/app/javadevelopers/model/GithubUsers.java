package org.andela.app.javadevelopers.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GithubUsers implements Parcelable {

    @SerializedName("login")
    String username;

    @SerializedName("url")
    String githublink;

    @SerializedName("avatar_url")
    String profileimg;

    protected GithubUsers(Parcel in) {
        username = in.readString();
        githublink = in.readString();
        profileimg = in.readString();
    }

    public static final Creator<GithubUsers> CREATOR = new Creator<GithubUsers>() {
        @Override
        public GithubUsers createFromParcel(Parcel in) {
            return new GithubUsers(in);
        }

        @Override
        public GithubUsers[] newArray(int size) {
            return new GithubUsers[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public String getGithublink() {
        return githublink;
    }

    public String getProfileimg() {
        return profileimg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(githublink);
        dest.writeString(profileimg);
    }
}
