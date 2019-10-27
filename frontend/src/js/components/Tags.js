import React, {Component} from "react";
import {Input, Tag, Tooltip, Icon, Button} from 'antd';

export default class Tags extends Component {
	constructor(props) {
    	super(props);
	    this.state = {
	    	tags: props.skills ? this.props.skills.map(skill => {
	    		return skill.name}):[],
	    	inputVisible: false,
	    	inputValue: '',
	    };
	}

	// change state when editting
	triggerChange = () => {
	    // Should provide an event to pass value to Form.
	    const { onChange } = this.props;
	    if (onChange) {
	      onChange({
	        skills: this.state.tags,
	      });
	    }
  	};

  	handleInputChange = e => {
		this.setState({ inputValue: e.target.value });
	};

  	// remove a tag
    handleClose = removedTag => {
		const tags = this.state.tags.filter(tag => tag !== removedTag);
		console.log(tags);
		this.setState({ tags }, this.triggerChange);
	};

	// show the pending tag
	showInput = () => {
		this.setState({ inputVisible: true }, () => this.input.focus());
	};

	// add a new tag
	handleInputConfirm = () => {
		const { inputValue } = this.state;
		let { tags } = this.state;
		if (inputValue && tags.indexOf(inputValue) === -1) {
		  tags = [...tags, inputValue.toUpperCase()];
		}
		this.setState({
		  tags: tags,
		  inputVisible: false,
		  inputValue: '',
		},this.triggerChange);
	};

	saveInputRef = input => (this.input = input);

    render() {
    	const { tags, inputVisible, inputValue } = this.state;
    	const color = ["magenta", "red", "volcano", "orange", "gold", "lime", "green", "cyan", "blue", "geekblue", "purple"];
    	return (
    		<span >
    		{tags.map((tag, index) => {
			  tag = tag.toUpperCase();
	          const isLongTag = tag.length > 20;
	          const tagElem = (
	            <Tag key={tag} color={color[Math.floor(Math.random()*color.length)]} closable={this.props.editable} onClose={() => this.handleClose(tag)}>
	              {isLongTag ? `${tag.slice(0, 20)}...` : tag}
	            </Tag>
	          );
	          return isLongTag ? (
	            <Tooltip title={tag} key={tag}>
	              {tagElem}
	            </Tooltip>
	          ) : (tagElem);
        	})}
        	{this.props.editable && inputVisible && (
	          <Input
	            ref={this.saveInputRef}
	            type="text"
	            size="small"
	            style={{ width: 78 }}
	            value={inputValue}
	            onChange={this.handleInputChange}
	            onBlur={this.handleInputConfirm}
	            onPressEnter={this.handleInputConfirm}
	          />
	        )}
	        {this.props.editable && !inputVisible && (
	          <Tag onClick={this.showInput} style={{ background: '#fff', borderStyle: 'dashed' }}>
	            <Icon type="plus" />New Tag
	          </Tag>
	        )}
    		</span>
    	)
    }
}