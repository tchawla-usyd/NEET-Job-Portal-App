import React, {Component} from "react";
import { Upload, Button, Icon } from 'antd';

import {UPLOAD} from "../constants/BackendAPI"

export default class Uploader extends Component {
  state = {
    fileList: this.props.resume == null ? [] : [
      {
        uid: '-1',
        name: this.props.resume.substring(this.props.resume.lastIndexOf('/')+1),
        status: 'done',
        url: this.props.resume,
      },
    ],
  };

  handleChange = info => {
    let fileList = [...info.fileList];

    // 1. Limit the number of uploaded files
    // Only to show one recent uploaded files, and old ones will be replaced by the new
    fileList = fileList.slice(-1);

    // 2. Read from response and show file link
    fileList = fileList.map(file => {
      if (file.response) {
        // Component will show file.url as link
        file.url = file.response.file_url;
      }
      return file;
    });
    this.setState({ fileList });
  };

  render() {
    // Auth
    const token = localStorage.getItem("token");
    const headers = {'Authorization': token};

    const props = {
      action: UPLOAD,
      onChange: this.handleChange,
      multiple: true,
      headers: headers,
    };
    return (
      <Upload {...props} fileList={this.state.fileList}>
        {this.props.editable ? <Button>
          <Icon type="upload" /> Upload
        </Button> : ''}
      </Upload>
    );
  }
}