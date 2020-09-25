import React from 'react'
import { Switch, Route} from 'react-router-dom'
import DetailMaster from './componet/detailMaster'
import Login from './componet/Login'

const Routes = () =>{
    return(
        <Switch>
            <Route exact path="/" component={Login}/>
            <Route path="/DetailMaster/:key" component={DetailMaster}/>
        </Switch>
    )
}

export default Routes;