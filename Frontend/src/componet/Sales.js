import React, { Component } from 'react'
import Navigation from './Navigation'
import ReactDOM from 'react-dom'
import axios from 'axios'
import { Table } from 'react-bootstrap';
import {Card, CardBody,CardHeader} from 'reactstrap';
import './sales.css'
import { CommonLoading } from 'react-loadingg';


class Sales extends Component{
    constructor(props){
        super(props)
        this.state={
            dataSaleGet:null,
            statusUser: false,
            stateDataSale: [],
            loading:false
        }
        const { match: { params } } = this.props;
        this.peticionGet(params.key)
    }

    peticionGet = async (dato)=>{
        await axios.get('http://localhost:7001/AS2_P2/api/sales?key='+dato).then(resp =>{
          this.setState({dataSaleGet : resp.data})
          if(this.state.dataSaleGet.idUser > 0){
              this.setState({statusUser : true})
          }else{
              console.log("error")
          }
        }).then(()=>{
          if(this.state.statusUser){
            ReactDOM.render(
                <Navigation dataM={this.state.dataSaleGet} stateButton={this.state.statusUser}/>, document.getElementById('Nav')
              )
          }
          this.peticionGetSales()
        }).catch(error =>{
          console.log(error)
        })
      }

      peticionGetSales = async()=>{
          await axios.get('http://localhost:7001/AS2_P2/api/sales').then(resp=>{
              console.log(resp.data)
              this.setState({stateDataSale: resp.data})
          }).then(()=>{
              this.setState({loading:true})
          }).catch(error=>{
              console.log(error)
          })
      }

    render(){
        return(
            <div id="containerSale">
                {this.state.loading?
            <Card>
            <CardHeader>Sales list</CardHeader>
            <CardBody>
                <Table striped bordered hover variant="dark">
                <thead>
                    <tr>
                        <td>ID</td>
                        <td>Customer</td>
                        <td>Nit</td>
                        <td>Address</td>
                        <td>Date</td>
                        <td>Cashier</td>
                        <td>Total Sale</td>
                    </tr>
                </thead>
                <tbody>
                    {this.state.stateDataSale.map(sale =>(
                        <tr key={sale.idSale}>
                            <td>{sale.idSale}</td>
                            <td>{sale.customer}</td>
                            <td>{sale.nitCustomer}</td>
                            <td>{sale.addressCustomer}</td>
                            <td>{sale.dateSale}</td>
                            <td>{sale.nameCashier}</td>
                            <td>{sale.saleTotal}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </CardBody>
        </Card>:<CommonLoading/> 
            }
            </div>  
        )
    }

}
export default Sales