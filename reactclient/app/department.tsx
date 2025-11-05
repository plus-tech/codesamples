'use client';

import * as React from "react";
import axios from 'axios';

import {
  RestEndpoint,
  PathFindAllDpts,
  ApiAccessKey,
} from "./util/constant.ts";

import {
  ApiFindAllDpts,
  ApiFindDptById,
  ApiInsertDpt,
  ApiUpdateDpt,
  ApiDeleteDpt,
} from "./service/apidpt.ts";

import styles from "./page.module.css";


export default function Department(){
  const [dptId, setDptId] = React.useState(null);
  const [dptList, setDptList] = React.useState(null);
  const [isVisible, setIsVisible] = React.useState(false);
  const refCreateBtn = React.useRef(null);

  const refDptForm = React.useRef(null);
  const [newDpt, setNewDpt] = React.useState({
    department_id: '',
    department_name: '',
    manager_id: '',
  });

  /*
   * Add the additional fields to each department object
   */
  const transformDptList = (dptlist) => {
    // let tmplist = [];
    // dptlist.map((dpt, index) => {
    //     tmplist.push({...dpt, id:dpt.department_id, checked:false, updated:false})
    //   }
    // )
    // console.log('adjusted dptlist: ', tmplist);
    //
    // setDptList(tmplist);
    setDptList(dptlist.map((dpt) =>
      dpt={...dpt, id:dpt.department_id, checked:false, updated:false}));
  };

  /*
   * Get all the departments from the backend
   */
  React.useEffect( () => {
    console.log("Api: ", RestEndpoint+ApiFindAllDpts);

    let ignore = false;
    ApiFindAllDpts()
      .then(response => {
        if (!ignore) {
          console.log("response: ", response);

          transformDptList(response.data);
        }
      })
      .catch(error =>{
        console.error(error);
      });

    return () => {
      ignore = true;
    }
  }, []);

  /*
   * Search for a particular department identified by the entered department id
   */
  function searchDpt(e){
    e.preventDefault();

    console.log("search dpt: ", dptId);

    if (dptId == null || dptId == ''){
      ApiFindAllDpts()
        .then(res => {
          transformDptList(res.data);
        })
        .catch(error =>{
          console.error(error);
        });
    } else {
      ApiFindDptById(dptId)
        .then(res => {
          transformDptList([res.data]);
        })
        .catch(error =>{
          console.error(error);
        });
    }

    hideCreateInputs();
  }

  /*
   * Create a new department
   */
  const onChange = (e) => {
    setNewDpt({...newDpt, [e.target.name]: e.target.value });
  }

  function CreateDpt(e){
    e.preventDefault();

    const btnCaption = e.target.innerText;
    if (btnCaption==="Submit"){
      console.log('Creating a dpt: ', newDpt);

      ApiInsertDpt(newDpt)
        .then(response => {
          console.log('Response data:', response.data);
          console.log('Status:', response.status);

          const tmpdpt = dptList.concat({...newDpt, id:newDpt.department_id, checked:false});
          const sorteddpt = [...tmpdpt].sort((a, b) => a.department_id - b.department_id);
          // console.log('List with the new dpt added: ', sorteddpt);
          setDptList(sorteddpt);
        })
        .catch(error =>{
          //
          // Sample codes categorizing errors in details, provided by Google AI
          if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            console.error('Server error:', error.response.data);
            console.error('Status code:', error.response.status);
          } else if (error.request) {
            // The request was made but no response was received
            console.error('No response received:', error.request);
          } else {
            // Something happened in setting up the request that triggered an Error
            console.error('Error setting up request:', error.message);
          }
        });

      e.target.innerText = "Create";
    } else {
      e.target.innerText = "Submit";
    }

    setIsVisible(!isVisible);
  }

  /*
   * Hide the input boxes
   */
  function hideCreateInputs(){
    setIsVisible(false);
    if (refCreateBtn != null){
      refCreateBtn.current.innerHTML = "Create";
    }
  }

  /*
   * Update the selected departments
   */
   const onUpdateChange = (e, department_id) => {
     setDptList((prevItems) =>
       prevItems.map((item) =>
         item.department_id === department_id ? {...item, [e.target.name]: e.target.value, updated:true} : item
       )
     );
   }

  const updateDpts = (e) =>{
    e.preventDefault();

    const updList = dptList.filter(tdpt => tdpt.updated === true);
    updList.forEach(tdpt => {
      let dpt = {
        department_id: tdpt.department_id,
        department_name: tdpt.department_name,
        manager_id: tdpt.manager_id
      }

      console.log('updating: ', dpt);

      ApiUpdateDpt(dpt)
      .then(response =>{
        console.log('Response data:', response.data);
        console.log('Status:', response.status);
      })
      .catch(error =>{
        console.error(error);
      })
    });

    if (updList.length > 0){
      setDptList((prevItems) =>
        prevItems.map((item) =>
          item.updated === true ? {...item, checked:false, updated:false} : item
        )
      );
    }
  }

  /*
   * Handle checkboxes
   */
  const handleHeaderCheckboxChange = (e) =>{
    const ischecked = e.target.checked;
    console.log("HeaderCheckbox is checked:", ischecked);

    setDptList((prevItems) =>
      prevItems.map((item) => item = {...item, checked:ischecked})
    )
  };

  const handleCheckboxChange = (id) => {
    setDptList((prevItems) =>
      prevItems.map((item) =>
        item.id === id ? { ...item, checked: !item.checked } : item
      )
    );
  };

  /*
   * Delete the selected departments
   */
  function deleteDpts(e){
    e.preventDefault();

    const delList = dptList.filter(dpt => dpt.checked === true);
    const currList = dptList.filter(dpt => dpt.checked === false);
    console.log('dpts to be deleted: ', delList);
    // for (const dpt in delList) { // didn't work, dpt gets 0
    delList.forEach((dpt, index) => {
      console.log('deleting: ', dpt);

      ApiDeleteDpt(dpt.department_id)
      .then(response =>{
        console.log('Response data:', response.data);
        console.log('Status:', response.status);
      })
      .catch(error =>{
        console.error(error);
      })
    });

    // dptList.map((dpt) => {
    //   if (dpt.checked){
    //     ApiDeleteDpt(dpt.department_id)
    //     .then(response =>{
    //       console.log('Department has been deleted:', response.data);
    //       console.log('Status:', response.status);
    //     })
    //     .catch(error =>{
    //       console.error(error);
    //     })
    //   }
    // })

    // for (let i = dptList.length - 1; i >= 0; i--){
    //   if (dptList[i].checked){
    //     ApiDeleteDpt(dptList[i].department_id)
    //     .then(response =>{
    //       console.log('Department has been deleted:', response.data);
    //       console.log('Status:', response.status);
    //       dptList.splice(i, 1);
    //     })
    //     .catch(error =>{
    //       console.error(error);
    //     })
    //   }
    // }

    if(dptList.length !== currList.length){
      setDptList(currList);
    }
  }


  return (
    <div className={styles.dpt}>
      <form ref={refDptForm}>
      <table id="dpttbl">
        <thead>
          <tr>
            <td></td>
            <td>Department ID</td>
            <td><input type="number"
              name="dptId"
              onChange={(e) => setDptId(e.target.value)} />
            </td>
            <td><button onClick={searchDpt}>Search</button> </td>
          </tr>
          <tr>
            <th>
              <input type="checkbox" onChange={handleHeaderCheckboxChange}/>
            </th>
            <th>Department ID</th>
            <th>Department Name</th>
            <th>Manager ID</th>
          </tr>
        </thead>
        <tbody>
          {
            dptList && dptList.map((dpt, index) => (
              <tr className="contents" key={index}>
                {/* Checkbox Column */}
                <td className="contents" >
                  <input type="checkbox"
                    id={dpt.id}
                    checked={dpt.checked}
                    onChange={() => handleCheckboxChange(dpt.id)}/>
                </td>
                {/* Department Id Column */}
                <td className="contents" >{dpt.department_id}</td>
                {/* Department Name Column */}
                <td className="contents" >
                  <input type="text"
                    readOnly={!dpt.checked}
                    name="department_name"
                    value={dpt.department_name}
                    onChange={(e)=>onUpdateChange(e, dpt.department_id)} />
                </td>
                {/* Manager Id Column */}
                <td className="contents" >
                  <input type="number"
                    readOnly={!dpt.checked}
                    name="manager_id"
                    value={dpt.manager_id}
                    onChange={(e)=>onUpdateChange(e, dpt.department_id)} />
                </td>
              </tr>
              )
            )
          }
        </tbody>
        <tfoot>
            {isVisible && (
              <tr>
                <td></td>
                <td><input className="forCreate" type="number" variant='flushed' name="department_id" value={newDpt.department_id} onChange={onChange} /> </td>
                <td><input className="forCreate" type="text" variant='flushed' name="department_name" value={newDpt.department_name} onChange={onChange} /> </td>
                <td><input className="forCreate" type="number" variant='flushed' name="manager_id" value={newDpt.manager_id} onChange={onChange} /> </td>
              </tr>
            )}
            <tr>
              <td></td>
              <td><button ref={refCreateBtn} onClick={CreateDpt}>Create</button> </td>
              <td><button onClick={updateDpts}>Update</button> </td>
              <td><button onClick={deleteDpts}>Delete</button> </td>
            </tr>
        </tfoot>
      </table>
      </form>
    </div>
  );
}
