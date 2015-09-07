package policycompass.fcmmanager.simulation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.megadix.jfcm.CognitiveMap;
import org.megadix.jfcm.Concept;
import org.megadix.jfcm.ConceptActivator;
import org.megadix.jfcm.FcmConnection;
import org.megadix.jfcm.act.*;
import org.megadix.jfcm.conn.*;

import policycompass.fcmmanager.hibernate.HibernateUtil;
import policycompass.fcmmanager.models.FCMConcept;
import policycompass.fcmmanager.models.FCMConnection;
import policycompass.fcmmanager.models.FCMModel;
import policycompass.fcmmanager.models.FCMSimulationConcept;
import policycompass.fcmmanager.models.FCMSimulationConnection;
import policycompass.fcmmanager.models.FCMSimulationDetail;
import policycompass.fcmmanager.models.FCMSimulationResult;

/*
c1 - International ban and prosecution
c2 - corruption
c3 - cocaine acreage
c4 - gainings
c5 - financial problem of customers
c6 - syndicates
c7 - availability of drugs
c8 - streetgangs
c9 - use of cocaine
c10 - cocaine price
c11 - national prohibition and prosecution
*/



public class pcjfcm {
	
	static int NumberIterations=20;
	
	public static FCMSimulationDetail runFCMSimulation(JSONObject jsonModel)
	{
		FCMModel model = new FCMModel();
		List<FCMSimulationConcept> simulationConcept = new ArrayList<FCMSimulationConcept>();
		List<FCMConcept> concept = new ArrayList<FCMConcept>();
		List<FCMConnection> connection = new ArrayList<FCMConnection>();
		List<FCMSimulationConnection> simulationConnection = new ArrayList<FCMSimulationConnection>();

		List<FCMSimulationResult> simulationResults = new ArrayList<FCMSimulationResult>();
		Concept[] c;
		double conceptOutput;

		int modelID;
        int conceptSimulationID = getConceptID();
        int connectionSimulationID = getConnectionID();
	
		Date date1=new Date();
        
		CognitiveMap map;

		SigmoidActivator afSigmoidActivator = new SigmoidActivator();
		afSigmoidActivator.setIncludePreviousOutput(false);

		CauchyActivator afCauchyActivator = new CauchyActivator();
		afCauchyActivator.setIncludePreviousOutput(false);

		GaussianActivator afGaussianActivator = new GaussianActivator();
		afGaussianActivator.setIncludePreviousOutput(false);

		HyperbolicTangentActivator afHyperbolicTangentActivator = new HyperbolicTangentActivator();
		afHyperbolicTangentActivator.setIncludePreviousOutput(false);

		IntervalActivator afIntervalActivator = new IntervalActivator();
		afIntervalActivator.setIncludePreviousOutput(false);

		LinearActivator afLinearActivator = new LinearActivator();
		afLinearActivator.setIncludePreviousOutput(false);

		NaryActivator afNaryActivator = new NaryActivator();
		afNaryActivator.setIncludePreviousOutput(false);

		SignumActivator afSignumActivator = new SignumActivator();
		afSignumActivator.setIncludePreviousOutput(false);
		
		int activatorID;

		try {
	    	modelID=Integer.parseInt(jsonModel.getJSONObject("data").getJSONObject("model").get("ModelID").toString());
		    model.setId(modelID);
			model.setTitle(jsonModel.getJSONObject("data").getJSONObject("model").get("title").toString());
			model.setDescription(jsonModel.getJSONObject("data").getJSONObject("model").get("description").toString());
			model.setKeywords(jsonModel.getJSONObject("data").getJSONObject("model").get("keywords").toString());
			
			activatorID = Integer.parseInt(jsonModel.getJSONObject("data").get("activatorId").toString());
			
			System.out.println(activatorID);
			
	        Session session = HibernateUtil.getSessionFactory().openSession();
	         
	        session.beginTransaction();
	        Query qConcept = session.createQuery("from fcmmanager_concepts where FCMModel_id= :id");
	        qConcept.setInteger("id", modelID);
	        @SuppressWarnings("unchecked")
	        List<FCMConcept> conceptdb = qConcept.list();
	        
	        Query qConnection = session.createQuery("from fcmmanager_connections where FCMModel_id= :id");
	        qConnection.setInteger("id", modelID);
	        @SuppressWarnings("unchecked")
	        List<FCMConnection> connectiondb = qConnection.list();

			map = new CognitiveMap(model.getTitle());

			JSONArray concepts = jsonModel.getJSONObject("data").getJSONArray("concepts");
			System.out.println(concepts.length());
			for(int i=0;i<concepts.length();i++){
				FCMSimulationConcept sCon = new FCMSimulationConcept();
				FCMConcept Con = new FCMConcept();
				JSONObject ob= concepts.getJSONObject(i);
				
				sCon.setId(conceptSimulationID);
				sCon.setFCMSimulation_id(modelID);
				sCon.setConceptID(Integer.parseInt(ob.getString("Id").toString()));
				sCon.setScaleValue(Double.parseDouble(ob.getString("value")));
				sCon.setFixedOutput(ob.getBoolean("fixedoutput"));
				sCon.setDateAddedtoPC(date1);
				sCon.setDateModified(date1);
				sCon.setUserID(Integer.parseInt(jsonModel.getJSONObject("data").get("userID").toString()));
				simulationConcept.add(sCon);

				Con.setFCMModelID(modelID);
				Con.setId(Integer.parseInt(ob.getString("Id").toString()));
				Con.setTitle(ob.getString("title").toString());
				Con.setScale(Integer.parseInt(ob.getString("scale").toString()));
				concept.add(Con);

		    	for (int j=0;j<conceptdb.size();j++)
		    	{
    				if (conceptdb.get(j).getId()==Integer.parseInt(ob.getString("Id").toString()))
    				{
						conceptdb.get(j).setValue(Double.parseDouble(ob.getString("value")));
						conceptdb.get(j).setMetric_id(ob.getInt("metricId"));
			        	session.update(conceptdb.get(j));
    				}
		    	}
		    	
				conceptSimulationID = conceptSimulationID+1;
			}			

			c = new Concept[simulationConcept.size()];
			
			System.out.println(simulationConcept.size());
			for (int i=0;i<simulationConcept.size();i++)
			{
//				System.out.println("c"+simulationConcept.get(i).getConceptID()+"\t"+concept.get(i).getTitle()+"\t"+simulationConcept.get(i).getScaleValue()+"\t"+simulationConcept.get(i).getFixedOutput());
				switch (activatorID)
				{
					case 1:
						Concept cc1 = new Concept("c"+simulationConcept.get(i).getConceptID(), concept.get(i).getTitle(), afCauchyActivator, 0.0, simulationConcept.get(i).getScaleValue(), simulationConcept.get(i).getFixedOutput());
						c[i]=cc1;
						break;
				
					case 2:
						Concept cc2 = new Concept("c"+simulationConcept.get(i).getConceptID(), concept.get(i).getTitle(), afGaussianActivator, 0.0, simulationConcept.get(i).getScaleValue(), simulationConcept.get(i).getFixedOutput());
						c[i]=cc2;
						break;
				
					case 3:
						Concept cc3 = new Concept("c"+simulationConcept.get(i).getConceptID(), concept.get(i).getTitle(), afHyperbolicTangentActivator, 0.0, simulationConcept.get(i).getScaleValue(), simulationConcept.get(i).getFixedOutput());
						c[i]=cc3;
						break;
				
					case 4:
						Concept cc4 = new Concept("c"+simulationConcept.get(i).getConceptID(), concept.get(i).getTitle(), afIntervalActivator, 0.0, simulationConcept.get(i).getScaleValue(), simulationConcept.get(i).getFixedOutput());
						c[i]=cc4;
						break;
				
					case 5:
						Concept cc5 = new Concept("c"+simulationConcept.get(i).getConceptID(), concept.get(i).getTitle(), afLinearActivator, 0.0, simulationConcept.get(i).getScaleValue(), simulationConcept.get(i).getFixedOutput());
						c[i]=cc5;
						break;
				
					case 6:
						Concept cc6 = new Concept("c"+simulationConcept.get(i).getConceptID(), concept.get(i).getTitle(), afNaryActivator, 0.0, simulationConcept.get(i).getScaleValue(), simulationConcept.get(i).getFixedOutput());
						c[i]=cc6;
						break;
				
					case 7:
						Concept cc7 = new Concept("c"+simulationConcept.get(i).getConceptID(), concept.get(i).getTitle(), afSigmoidActivator, 0.0, simulationConcept.get(i).getScaleValue(), simulationConcept.get(i).getFixedOutput());
						c[i]=cc7;
						break;
				
					case 8:
						Concept cc8 = new Concept("c"+simulationConcept.get(i).getConceptID(), concept.get(i).getTitle(), afSignumActivator, 0.0, simulationConcept.get(i).getScaleValue(), simulationConcept.get(i).getFixedOutput());
						c[i]=cc8;
				
				}

				map.addConcept(c[i]);
				System.out.println("c"+simulationConcept.get(i).getConceptID());
			}
			
//			for (int i=0;i<c.length;i++)
//			{
//				System.out.println(c[i].getName()+"\t"+c[i].getName()+"\t"+c[i].getDescription()+"\t"+c[i].getOutput());
//			}
			
			JSONArray connections = jsonModel.getJSONObject("data").getJSONArray("connections");
			for(int i=0;i<connections.length();i++){
				FCMSimulationConnection sCon = new FCMSimulationConnection();
				FCMConnection con = new FCMConnection();
				JSONObject ob= connections.getJSONObject(i);
				
				sCon.setId(connectionSimulationID);
				sCon.setFCMSimulation_id(modelID);
				sCon.setConnectionID(Integer.parseInt(ob.getString("Id").toString()));
				sCon.setWeighted(Double.parseDouble(ob.getString("weighted")));
				sCon.setDateAddedtoPC(date1);
				sCon.setDateModified(date1);
				sCon.setUserID(Integer.parseInt(jsonModel.getJSONObject("data").get("userID").toString()));
				simulationConnection.add(sCon);

				con.setFCMModelID(modelID);
				con.setId(Integer.parseInt(ob.getString("Id").toString()));
				con.setConceptFrom(Integer.parseInt(ob.getString("sourceID").toString()));
				con.setConceptTo(Integer.parseInt(ob.getString("destinationID").toString()));
				connection.add(con);

		    	for (int j=0;j<connectiondb.size();j++)
		    	{
    				if (connectiondb.get(j).getId()==Integer.parseInt(ob.getString("Id").toString()))
    				{
    					connectiondb.get(j).setWeight(ob.getString("weighted"));;
    		        	session.update(connectiondb.get(j));
    				}
		    	}
		    	
				connectionSimulationID=connectionSimulationID+1;
				
				FcmConnection conn = new WeightedConnection("c"+con.getConceptFrom()+" -> "+"c"+con.getConceptTo(), ob.getJSONObject("source").getString("title")+" -> "+ob.getJSONObject("destination").getString("title"), sCon.getWeighted());	
				map.addConnection(conn);
				map.connect("c"+con.getConceptFrom(), "c"+con.getConceptFrom()+" -> "+"c"+con.getConceptTo(), "c"+con.getConceptTo());
			}			

	        session.getTransaction().commit();
	        session.clear();
	        session.close();

	        DecimalFormat df2 = new DecimalFormat("#.###");
			
			for(int i=0;i<NumberIterations;i++)
			{
				for (int j=0;j<simulationConcept.size();j++)
				{
					FCMSimulationResult res = new FCMSimulationResult();
					res.setId(i+1);
					res.setIteration_id(i+1);
					res.setConceptID(simulationConcept.get(j).getConceptID());
					res.setInput(simulationConcept.get(j).getScaleValue());
					res.setFCMSimulation_id(model.getId());
					
					if (c[j].getOutput()==null)
						res.setOutput(0.0);
					else
					{
						conceptOutput = Double.parseDouble(df2.format(c[j].getOutput()));
						if (conceptOutput<=0.2)
							res.setOutput(0.2);
						else if (conceptOutput<=0.4)
							res.setOutput(0.4);
						else if (conceptOutput<=0.6)
							res.setOutput(0.6);
						else if (conceptOutput<=0.8)
							res.setOutput(0.8);
						else if (conceptOutput<=1.0)
							res.setOutput(1.0);
//						res.setOutput(Double.parseDouble(df2.format(c[j].getOutput())));
					}
//					System.out.println((j+1)+"\t"+c[j].getName()+"\t"+c[j].getDescription()+"\t"+c[j].getOutput());
					simulationResults.add(res);
				}
				map.execute();
			}
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		FCMSimulationDetail FCMSimulation = new FCMSimulationDetail();
		FCMSimulation.setModel(model);
		FCMSimulation.setResult(simulationResults);
		
		return(FCMSimulation);
	}

	public static FCMSimulationDetail runImpactAnalysis(int id, JSONObject jsonModel)
	{
		FCMModel model = new FCMModel();
		List<List<FCMSimulationConcept>> listSimulationConcept = new ArrayList<List<FCMSimulationConcept>>();
		List<FCMSimulationConcept> simulationConcept = new ArrayList<FCMSimulationConcept>();
		List<FCMConcept> concept = new ArrayList<FCMConcept>();
		List<FCMConnection> connection = new ArrayList<FCMConnection>();
		List<FCMSimulationConnection> simulationConnection = new ArrayList<FCMSimulationConnection>();

		List<FCMSimulationResult> simulationResults = new ArrayList<FCMSimulationResult>();
		double conceptOutput;

		int modelID;
        int conceptSimulationID = getConceptID();
        int connectionSimulationID = getConnectionID();
        
        int selectedConceptID, selectedConceptID1, selectedConceptID2, selectedConceptID3;
	
		Date date1=new Date();
        
		SigmoidActivator af = new SigmoidActivator();
		af.setIncludePreviousOutput(false);

		try {
	    	modelID=Integer.parseInt(jsonModel.getJSONObject("data").getJSONObject("model").get("ModelID").toString());
		    model.setId(modelID);
			model.setTitle(jsonModel.getJSONObject("data").getJSONObject("model").get("title").toString());
			model.setDescription(jsonModel.getJSONObject("data").getJSONObject("model").get("description").toString());
			model.setKeywords(jsonModel.getJSONObject("data").getJSONObject("model").get("keywords").toString());
			
			JSONArray concepts = jsonModel.getJSONObject("data").getJSONArray("concepts");
			for(int i=0;i<concepts.length();i++)
			{
				JSONObject ob= concepts.getJSONObject(i);
				FCMConcept Con = new FCMConcept();
				FCMSimulationConcept sCon = new FCMSimulationConcept();
				
				sCon.setId(conceptSimulationID);
				sCon.setFCMSimulation_id(modelID);
				sCon.setConceptID(Integer.parseInt(ob.getString("Id").toString()));
				sCon.setScaleValue(Double.parseDouble(ob.getString("value")));
				sCon.setFixedOutput(ob.getBoolean("fixedoutput"));
//				sCon.setMetricID(ob.getInt("x"));
				sCon.setDateAddedtoPC(date1);
				sCon.setDateModified(date1);
				sCon.setUserID(Integer.parseInt(jsonModel.getJSONObject("data").get("userID").toString()));
				simulationConcept.add(sCon);

				Con.setFCMModelID(modelID);
				Con.setId(Integer.parseInt(ob.getString("Id").toString()));
				Con.setTitle(ob.getString("title").toString());
				Con.setScale(Integer.parseInt(ob.getString("scale").toString()));
				concept.add(Con);
				conceptSimulationID = conceptSimulationID+1;
			}			
			if (id==1)
			{
				selectedConceptID = Integer.parseInt(jsonModel.getJSONObject("data").getJSONObject("selectedConcept").get("Id").toString());
			
				setSimulationConceptValues(listSimulationConcept, simulationConcept, selectedConceptID);
			}
			else
			{
				selectedConceptID1 = Integer.parseInt(jsonModel.getJSONObject("data").getJSONObject("selectedConcept1").get("Id").toString());
				selectedConceptID2 = Integer.parseInt(jsonModel.getJSONObject("data").getJSONObject("selectedConcept2").get("Id").toString());
				selectedConceptID3 = Integer.parseInt(jsonModel.getJSONObject("data").getJSONObject("selectedConcept3").get("Id").toString());
				
				setSimulationConceptValues(listSimulationConcept, simulationConcept, selectedConceptID1, selectedConceptID2);
			}
			
			JSONArray connections = jsonModel.getJSONObject("data").getJSONArray("connections");
			for(int i=0;i<connections.length();i++)
			{
				FCMSimulationConnection sCon = new FCMSimulationConnection();
				FCMConnection con = new FCMConnection();
				JSONObject ob= connections.getJSONObject(i);
				
				sCon.setId(connectionSimulationID);
				sCon.setFCMSimulation_id(modelID);
				sCon.setConnectionID(Integer.parseInt(ob.getString("Id").toString()));
				sCon.setWeighted(Double.parseDouble(ob.getString("weighted")));
				sCon.setDateAddedtoPC(date1);
				sCon.setDateModified(date1);
				sCon.setUserID(Integer.parseInt(jsonModel.getJSONObject("data").get("userID").toString()));
				simulationConnection.add(sCon);

				con.setFCMModelID(modelID);
				con.setId(Integer.parseInt(ob.getString("Id").toString()));
				con.setConceptFrom(Integer.parseInt(ob.getString("sourceID").toString()));
				con.setConceptTo(Integer.parseInt(ob.getString("destinationID").toString()));
				connection.add(con);

				connectionSimulationID=connectionSimulationID+1;
			}			

			DecimalFormat df1 = new DecimalFormat("#.#");
			DecimalFormat df2 = new DecimalFormat("#.###");

			for (int x=0;x<listSimulationConcept.size();x++)
			{
				CognitiveMap map = new CognitiveMap(model.getTitle());
				Concept[] c = new Concept[simulationConcept.size()];
				
				for (int i=0;i<listSimulationConcept.get(x).size();i++)
				{
//					System.out.println("c"+listSimulationConcept.get(x).get(i).getConceptID()+"\t"+concept.get(i).getTitle()+"\t"+listSimulationConcept.get(x).get(i).getScaleValue()+"\t"+listSimulationConcept.get(x).get(i).getFixedOutput());
					Concept cc = new Concept("c"+listSimulationConcept.get(x).get(i).getConceptID(), concept.get(i).getTitle(), af, 0.0, listSimulationConcept.get(x).get(i).getScaleValue(), listSimulationConcept.get(x).get(i).getFixedOutput());
					c[i]=cc;
					map.addConcept(c[i]);
				}
				
				for (int i=0;i<simulationConnection.size();i++)
				{
					String source="", destination="";
					for (int j=0;j<concept.size();j++)
					{
						if (connection.get(i).getConceptFrom()==concept.get(j).getId())
							source=concept.get(j).getTitle();
						if (connection.get(i).getConceptTo()==concept.get(j).getId())
							destination=concept.get(j).getTitle();
					}
					
					FcmConnection conn = new WeightedConnection("c"+connection.get(i).getConceptFrom()+" -> "+"c"+connection.get(i).getConceptTo(), source+" -> "+destination, simulationConnection.get(i).getWeighted());	
					map.addConnection(conn);
					map.connect("c"+connection.get(i).getConceptFrom(), "c"+connection.get(i).getConceptFrom()+" -> "+"c"+connection.get(i).getConceptTo(), "c"+connection.get(i).getConceptTo());
				}
	
				for(int i=0;i<NumberIterations;i++)
					map.execute();
	
				for (int j=0;j<simulationConcept.size();j++)
				{
					FCMSimulationResult res = new FCMSimulationResult();
					res.setId(1);
					res.setIteration_id(x+1);
					res.setConceptID(listSimulationConcept.get(x).get(j).getConceptID());
					res.setInput(listSimulationConcept.get(x).get(j).getScaleValue());
					res.setFCMSimulation_id(model.getId());
					if (c[j].getOutput()==null)
						res.setOutput(0.0);
					else
					{
						conceptOutput = Double.parseDouble(df2.format(c[j].getOutput()));
						if (conceptOutput<=0.2)
							res.setOutput(0.2);
						else if (conceptOutput<=0.4)
							res.setOutput(0.4);
						else if (conceptOutput<=0.6)
							res.setOutput(0.6);
						else if (conceptOutput<=0.8)
							res.setOutput(0.8);
						else if (conceptOutput<=1.0)
							res.setOutput(1.0);
//						res.setOutput(Double.parseDouble(df2.format(c[j].getOutput())));
					}
				
					simulationResults.add(res);
				}
			}			
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
		
		FCMSimulationDetail FCMSimulation = new FCMSimulationDetail();
		FCMSimulation.setModel(model);
		FCMSimulation.setResult(simulationResults);
		
		return(FCMSimulation);
	}
	

	public static void setSimulationConceptValues(List<List<FCMSimulationConcept>> listSimulationConcept, List<FCMSimulationConcept> simulationConcept, int ConceptID)
	{
		double[] scaleVal= new double[5];
		scaleVal[0] = 0.2;
		scaleVal[1] = 0.4;
		scaleVal[2] = 0.6;
		scaleVal[3] = 0.8;
		scaleVal[4] = 1.0;
		
		for (int x=0;x<5;x++)
		{
			List<FCMSimulationConcept> SimulationConcept = new ArrayList<FCMSimulationConcept>();
			for (int i=0;i<simulationConcept.size();i++)
			{
				FCMSimulationConcept sCon = new FCMSimulationConcept();
				
				sCon.setId(simulationConcept.get(i).getId());
				sCon.setFCMSimulation_id(simulationConcept.get(i).getFCMSimulation_id());
				sCon.setConceptID(simulationConcept.get(i).getConceptID());
				if (simulationConcept.get(i).getConceptID()==ConceptID)
					sCon.setScaleValue(scaleVal[x]);
				else
					sCon.setScaleValue(simulationConcept.get(i).getScaleValue());
				sCon.setFixedOutput(simulationConcept.get(i).getFixedOutput());
				SimulationConcept.add(sCon);
			}
			listSimulationConcept.add(SimulationConcept);
		}
	}
	
	public static void setSimulationConceptValues(List<List<FCMSimulationConcept>> listSimulationConcept, List<FCMSimulationConcept> simulationConcept, int ConceptID1, int ConceptID2)
	{
		double[] scaleVal= new double[5];
		scaleVal[0] = 0.2;
		scaleVal[1] = 0.4;
		scaleVal[2] = 0.6;
		scaleVal[3] = 0.8;
		scaleVal[4] = 1.0;
		
		for (int x=0;x<5;x++)
		{
			for (int i=0;i<simulationConcept.size();i++)
			{
				if (simulationConcept.get(i).getConceptID()==ConceptID2)
				{
					simulationConcept.get(i).setScaleValue(scaleVal[x]);
					setSimulationConceptValues(listSimulationConcept, simulationConcept, ConceptID1);
				}
			}
		}
	}
	
	public static int getConceptID() {
		int conceptID=0;
		
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Criteria criteria = session
	    .createCriteria(FCMConcept.class)
	    .setProjection(Projections.max("id"));
        if (criteria.uniqueResult()==null)
        {
        	conceptID=0;
        } else {
            conceptID = (Integer)criteria.uniqueResult();
        }
        session.clear();
        session.close();
        
		return (conceptID+1);
	}
	
	public static int getConnectionID() {
		int connectionID=0;
		
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Criteria criteria = session
	    .createCriteria(FCMConnection.class)
	    .setProjection(Projections.max("id"));
        if (criteria.uniqueResult()==null)
        {
        	connectionID=0;
        } else {
            connectionID = (Integer)criteria.uniqueResult();
        }
        session.clear();
        session.close();
        
		return (connectionID+1);
	}
	
}