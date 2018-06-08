import React, { Component } from 'react';
import ReactDataGrid   from 'react-data-grid';
import {
  Badge,
  Row,
  Col,
  FormGroup,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  CardTitle,
  Button,
  ButtonToolbar,
  ButtonGroup,
  ButtonDropdown,
  Dropdown, DropdownToggle, DropdownMenu, DropdownItem,
  Label,
  Input,
  Table
} from 'reactstrap';
import fetch from 'node-fetch';
import  FormData from 'form-data';
import moment from 'moment';
import PropTypes from 'prop-types';

var self;
var style = {
	"textAlign" : 'right'
};

class RowRenderer extends React.Component {
  constructor(props) {
    super(props)
    var propTypes = {
      idx: PropTypes.string.isRequired
    };
    this.state = {
      propTypes : propTypes
    }
  }

  getRowStyle(row) {
    return {
      color: this.props.value === "Invalid" ? 'red' : 'green'
    };
  };

  render() {
    return ( <span style={this.getRowStyle()}>{this.props.value}</span>);
  }
}

class ProviderMapping extends React.Component {
	constructor(props, context) {
	  super(props, context);
    this.state = {
      file:null,
      errorMessage : null,
      providerOpen : false,
      providerNameAndFileList: [],
      providerList: [],
      selectedProvider: [],
      providerFileList: [],
      selectedProviderFile: [],
      fileHeaders: [],
      databaseHeaders: [],
      mapping: {},
      savedMapping: {},
    };
    self = this;
    this.handleProviderSelect = this.handleProviderSelect.bind(this);
    this.handleProviderFileSelect = this.handleProviderFileSelect.bind(this);
    this.fetchFileHeaders = this.fetchFileHeaders.bind(this);
    this.handleMappingStore = this.handleMappingStore.bind(this);
    this.saveMapping = this.saveMapping.bind(this);
  }

  componentDidMount() {
      fetch('http://139.162.5.110:8080/getProvidersFTP', {
        method: 'GET'
    }).then(function(res1) {
      if (!res1.ok) {
        if (error.message) {
          self.setState({errorMessage :error.message});
        } 
      }
      return res1.json();
    }).then(function(response)   {
      let providers = Object.keys(response);
      self.setState({providerNameAndFileList: response, providerList: providers});
    });
   }

   handleProviderSelect(event) {
    if(event.target.value !== 'Please select a provider')
    {
      let fileList = this.state.providerNameAndFileList[event.target.value];
      this.setState({selectedProvider: event.target.value, 
        providerFileList: fileList,
        selectedProviderFile: []
      });
      document.getElementById("providerFileSelect").value = 'Please select a file format';
    } else {
      this.setState({selectedProvider: [], providerFileList: []});
    }
  }

  handleMappingStore(event) {
    let old_mapping = this.state.mapping === null ? {} : this.state.mapping; 
    let header = event.target.getAttribute('header');
    old_mapping[header] = event.target.value;
    console.log("heyo", old_mapping);
    this.setState({mapping: old_mapping});
    
  }

  handleProviderFileSelect(event) {

    if(event.target.value !== 'Please select a file format') {
      this.setState({selectedProviderFile: event.target.value});
      this.setState({fileHeaders: [], databaseHeaders:[]});
    } else {
      this.setState({selectedProviderFile: []});
    }
  }

  saveMapping(event) {
    console.log('saving mapping to db');

    // if there are fileheaders
    // this is there so that in case user selects the provider and file format
    // but does not click fetch
    // then we do nothing on submit to not allowing errorneous values 
    if(this.state.fileHeaders.length !== 0 ) {    
      const formData = new FormData();
      formData.append('provider', this.state.selectedProvider);
      formData.append('filename', this.state.selectedProviderFile);
      formData.append('mapping', JSON.stringify(this.state.mapping));

      fetch('http://139.162.5.110:8080/savemapping', {
        method: 'POST',
        body: formData 
      })

      // clear up the mapping
      this.setState({mapping: {}});
    }
  }

  fetchFileHeaders(event) {
    console.log(`Provider: ${this.state.selectedProvider} file: ${this.state.selectedProviderFile}`);
   
    if(this.state.selectedProvider !== 'undefined' && 
      this.state.selectedProvider.length > 0 &&
      this.state.selectedProviderFile !== 'undefined' &&
      this.state.selectedProviderFile.length > 0) {

        // fetch the form headers for the file
        // '\' is used to divide template literal over several lines
        fetch('http://139.162.5.110:8080/getMappingData?provider=' +
          self.state.selectedProvider + '&filename=' + 
          self.state.selectedProviderFile, {
          method: 'GET'
      }).then(function(res1) {
        if (!res1.ok) {
          if (error.message) {
            self.setState({errorMessage :error.message});
          } 
        }
        return res1.json();
      }).then(function(response) {
        console.log(response);
        //console.log({fileHeaders: response.fileHeaders, databaseHeaders: response.databaseColumnNames, mapping: response.headerMappingList});
        self.setState({
          fileHeaders: response.fileHeaders, 
          databaseHeaders: response.databaseColumnNames, 
          mapping: response.headerMappingList});
      });
    } else {
      self.setState({fileHeaders: [], databaseHeaders: []});
    }
  }

  render() {
    // unfortunately, i do not have any other solution other than doing this.
    let localHandleMappingStore = this.handleMappingStore;
    let dbheaders = this.state.databaseHeaders;
    let localSavedMapping = this.state.mapping;
    return (
      <div className="animated fadeIn">
        <FormGroup row>
          <Col md="2">
          </Col>
          <Col xs="12" md="4" >
            <FormGroup >
              <Col md="12">
                <Label htmlFor="ccyear">Provider Name</Label>
                <Input type="select" name="provider" id="providerSelect" onChange={this.handleProviderSelect}>
                  <option>Please select a provider</option>
                  {
                    this.state.providerList.map(function(provider, i) {
                    return <option key={i}>{provider}</option>
                    })
                  }
                </Input>
              </Col> 
            </FormGroup>  
          </Col>
          <Col xs="12" md="4">
            <FormGroup >
              <Col md="12">
                <Label htmlFor="ccyear">Format </Label>
                <Input type="select" name="format" id="providerFileSelect" onChange={this.handleProviderFileSelect}>
                  <option>Please select a file format</option>
                  {
                    this.state.providerFileList.map(function(file, i) {
                      return <option key={i}>{file}</option>
                    })
                  }
                </Input>
              </Col>
            </FormGroup>
          </Col>
          <Col md="2" > 
          </Col>
        </FormGroup>
        <FormGroup row>
          <Col md="4">
          </Col>
          <Col xs="12" md="4">
            <Button  block onClick={this.fetchFileHeaders}>Fetch</Button>
          </Col>
          <Col md="4">
          </Col>
        </FormGroup>
        <FormGroup row>
          <Col md="1">
          </Col>
          <Col xs="12" md="10">
            <Card>
                <CardHeader>
                  <center><strong>Mapping Table Data</strong></center>
                </CardHeader>
                <CardBody>
                  <FormGroup row>
                    <Col md="1">
                    </Col>
                    <Col xs="12" md="5">
                      <Label htmlFor="select">
                          <strong>Provider File Column Name</strong>
                      </Label>
                    </Col>
                    <Col xs="12" md="5">
                     <Label htmlFor="select"><strong>Select Database Column Mapping</strong></Label>
                    </Col>
                    <Col md="1">
                    </Col>
                  </FormGroup>
                  {
                    this.state.fileHeaders.map(function(header, i) {
                      return(
                        <FormGroup row>
                          <Col md="1">
                          </Col>
                          <Col xs="12" md="5">
                            <Label htmlFor="select" key={i}>{header}</Label>
                          </Col>
                          <Col xs="12" md="5">
                            <Input type="select" name="select" id={i} header= {header} onChange={localHandleMappingStore}>
                              <option value="0">Please select</option>
                              {
                                dbheaders.map(function (dataBaseColumn, i) {
                                  if(localSavedMapping !== null){
                                    return <option selected={dataBaseColumn === localSavedMapping[header] } key={dataBaseColumn}>{dataBaseColumn}</option>;
                                  }
                                  else {
                                    return <option key={dataBaseColumn}>{dataBaseColumn}</option>;
                                  }
                                  
                                })
                              }
                            </Input>
                          </Col>
                        </FormGroup>
                      );
                    })
                  }
                  <FormGroup row>
                    <Col xs="12" md="4">
                    </Col>
                    <Col xs="12" md="4">
                      <Button  block onClick={this.saveMapping}>Submit</Button>
                    </Col>
                    <Col xs="12" md="4">
                    </Col>
                  </FormGroup>
                </CardBody>
            </Card>
          </Col>
          <Col md="1">
          </Col>  
        </FormGroup> 	
      </div>
    );
  }
}

export default ProviderMapping;